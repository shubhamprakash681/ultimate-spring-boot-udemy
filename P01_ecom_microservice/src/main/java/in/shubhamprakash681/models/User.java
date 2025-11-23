package in.shubhamprakash681.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Scope("prototype")
@Data
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
