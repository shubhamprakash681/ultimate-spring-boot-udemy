package in.shubhamprakash681.order.dto.user;

import in.shubhamprakash681.order.enums.user.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDto address;
}
