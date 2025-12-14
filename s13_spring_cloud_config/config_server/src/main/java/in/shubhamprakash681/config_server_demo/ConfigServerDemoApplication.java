package in.shubhamprakash681.config_server_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerDemoApplication {

	static void main(String[] args) {
		SpringApplication.run(ConfigServerDemoApplication.class, args);
	}

}
