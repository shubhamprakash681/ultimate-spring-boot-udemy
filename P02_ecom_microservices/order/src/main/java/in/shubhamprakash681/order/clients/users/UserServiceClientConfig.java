package in.shubhamprakash681.order.clients.users;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Configuration
public class UserServiceClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilderForUserClient() {
        return WebClient.builder();
    }

    @Bean
    public UserServiceClient userServiceClient() {
        WebClient webClient = webClientBuilderForUserClient()
                .baseUrl("http://user-service")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, (_res) -> Mono.empty())
                .build();

        WebClientAdapter adapter = WebClientAdapter.create(webClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(UserServiceClient.class);
    }
}
