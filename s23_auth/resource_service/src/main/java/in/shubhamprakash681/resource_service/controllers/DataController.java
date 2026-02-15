package in.shubhamprakash681.resource_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    @GetMapping("/data")
    public String getData() {
        return "Hello from Resource Server";
    }
}
