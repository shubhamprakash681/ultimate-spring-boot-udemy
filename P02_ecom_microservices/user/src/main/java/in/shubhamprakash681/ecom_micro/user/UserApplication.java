package in.shubhamprakash681.ecom_micro.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {

	static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
