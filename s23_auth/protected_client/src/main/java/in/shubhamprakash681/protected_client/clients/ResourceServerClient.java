package in.shubhamprakash681.protected_client.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ResourceServerClient {
    private final RestTemplate restTemplate;
    private final OAuth2AuthorizedClientManager manager;

    @Value("${resource-service.uri}")
    String resourceServiceUrl;

    public String fetchData() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String token;
        if (auth instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            //    Using incoming Access token
            token = jwtAuthenticationToken.getToken().getTokenValue();
        } else {
            //    Creating new Access Token
            var authRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId("keycloak-client")
                    .principal("machine")
                    .build();

            var client = manager.authorize(authRequest);

            assert client != null;
            token = client.getAccessToken().getTokenValue();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);

        var response = restTemplate.exchange(resourceServiceUrl + "/data", HttpMethod.GET,
                new HttpEntity<>(httpHeaders), String.class);

        //        return response.toString();
        return response.getBody();
    }
}
