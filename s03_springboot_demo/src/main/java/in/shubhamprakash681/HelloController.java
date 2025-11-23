package in.shubhamprakash681;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @PostMapping("/hello")
    public String hello(@RequestBody String name) {

        return "Hello " + name + "!";
    }

}
