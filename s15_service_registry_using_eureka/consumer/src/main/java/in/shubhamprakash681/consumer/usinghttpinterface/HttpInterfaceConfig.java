package in.shubhamprakash681.consumer.usinghttpinterface;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpInterfaceConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilderForHttpInterface() {
        return WebClient.builder();
    }

    @Bean
    public HttpInterfaceService webClientHttpInterface() {
        WebClient webClient = webClientBuilderForHttpInterface()
                .baseUrl("http://provider")
                .build();

        WebClientAdapter adapter = WebClientAdapter.create(webClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(HttpInterfaceService.class);
    }
}
