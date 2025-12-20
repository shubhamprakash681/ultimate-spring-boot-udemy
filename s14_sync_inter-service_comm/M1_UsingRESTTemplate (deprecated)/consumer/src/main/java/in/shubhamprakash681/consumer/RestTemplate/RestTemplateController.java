package in.shubhamprakash681.consumer.RestTemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/rest-template")
public class RestTemplateController {
    @GetMapping("/instance")
    public String getInstance() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8081/instance-info", String.class);
    }
}
