package in.blogspot.javawithgaurav.springcloudsleuth.sampelapp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "in.blogspot.javawithgaurav.springcloudsleuth.sampleapp.controller",
		"in.blogspot.javawithgaurav.springcloudsleuth.sampleapp.config" })
@EnableAsync
public class SpringBootStartMain {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStartMain.class, args);
	}

}
