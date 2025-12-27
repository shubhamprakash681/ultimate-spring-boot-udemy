package in.shubhamprakash681.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {

	static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
