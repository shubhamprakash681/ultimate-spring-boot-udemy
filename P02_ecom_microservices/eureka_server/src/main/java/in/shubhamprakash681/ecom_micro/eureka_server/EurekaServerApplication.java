package in.shubhamprakash681.ecom_micro.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
