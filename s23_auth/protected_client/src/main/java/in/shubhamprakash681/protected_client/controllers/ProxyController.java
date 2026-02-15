package in.shubhamprakash681.protected_client.controllers;

import in.shubhamprakash681.protected_client.clients.ResourceServerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProxyController {
    private final ResourceServerClient resourceServerClient;

    @GetMapping("/proxy")
    public String getResServerData() {
        return resourceServerClient.fetchData();
    }
}
