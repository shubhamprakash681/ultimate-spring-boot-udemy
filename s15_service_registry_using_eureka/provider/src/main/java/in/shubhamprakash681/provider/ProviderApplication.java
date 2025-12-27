package in.shubhamprakash681.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderApplication {

	static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}

}
