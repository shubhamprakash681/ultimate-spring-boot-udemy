package in.shubhamprakash681.ecom_micro.order.clients.products;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Configuration
public class ProductServiceClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilderForProductClient() {
        return WebClient.builder();
    }

    @Bean
    public ProductServiceClient productServiceClient() {
        WebClient webClient = webClientBuilderForProductClient()
                .baseUrl("http://product")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, (_) -> Mono.empty())
                .build();

        WebClientAdapter adapter = WebClientAdapter.create(webClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(ProductServiceClient.class);
    }
}