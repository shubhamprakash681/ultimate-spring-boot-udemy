package in.shubhamprakash681.consumer.restClient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest-client")
public class RestClientController {
    private final RestClientService restClientService;

    @GetMapping("/instance")
    public String getInstance() {
        return restClientService.getInstanceInfo();
    }
}
