package in.blogspot.javawithgaurav.springcloudsleuth.sampleapp.controller;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Trace;
import org.springframework.cloud.sleuth.instrument.TraceCallable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	private static final Logger log = Logger.getLogger(HomeController.class);
	@Autowired private ExecutorService executorService;
	@Autowired private Trace trace;
	
	@RequestMapping(value="/")
	public String getUid() {
		log.info("<Start fetching UID>");
		final Trace t = this.trace;
		final ExecutorService es = this.executorService;
		Future<String> result = es.submit(new TraceCallable<>(t, new UidFetchTask()));
		while (!result.isDone()) {
			
		}
		
		String uid = null;
		try {
			uid = result.get();
			log.info("<Got uid with value: " + uid);
		} catch (InterruptedException | ExecutionException e) {
			log.error(e);
		}
		return uid;
	}
	
	class UidFetchTask implements Callable<String> {
		@Override
		public String call() throws Exception {
			log.info("<Start generating UID from task>");
			try {
				TimeUnit.MILLISECONDS.sleep(500l);
			}
			catch(InterruptedException ie) {
				log.error(ie);
			}
			String uid = UUID.randomUUID().toString();
			log.info("<Generated UID is: " + uid + " >");
			return uid;
		}
	}
}
