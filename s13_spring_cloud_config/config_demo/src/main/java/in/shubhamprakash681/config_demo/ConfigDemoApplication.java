package in.shubhamprakash681.config_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class ConfigDemoApplication {

	static void main(String[] args) {
		SpringApplication.run(ConfigDemoApplication.class, args);
	}

}
