package in.shubhamprakash681.auth_code_flow_demo_client.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(OAuth2AuthenticationToken token) {
        assert token.getPrincipal() != null;
//        System.out.println(token);
        String email  = token.getPrincipal().getAttribute("email");
        String name  = token.getPrincipal().getAttribute("name");
        String roles = token.getAuthorities().toString();
        return "Welcome, Hello " + name + "!!\nEmail: " + email + "\nRoles: " + roles;
    }
}
