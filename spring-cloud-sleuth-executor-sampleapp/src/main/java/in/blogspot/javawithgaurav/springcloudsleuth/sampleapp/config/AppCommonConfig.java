package in.blogspot.javawithgaurav.springcloudsleuth.sampleapp.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.sleuth.autoconfig.TraceAutoConfiguration;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(value=TraceAutoConfiguration.class)
public class AppCommonConfig {
	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(2);
	}
	
	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}
}
