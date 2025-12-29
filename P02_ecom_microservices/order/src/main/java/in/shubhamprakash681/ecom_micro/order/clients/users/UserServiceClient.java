package in.shubhamprakash681.ecom_micro.order.clients.users;

import in.shubhamprakash681.ecom_micro.order.dto.user.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(accept = "application/json", contentType = "application/json")
public interface UserServiceClient {
    @GetExchange("/api/users/{userId}")
    UserResponse getUserDetails(@PathVariable("userId") long id);
}
