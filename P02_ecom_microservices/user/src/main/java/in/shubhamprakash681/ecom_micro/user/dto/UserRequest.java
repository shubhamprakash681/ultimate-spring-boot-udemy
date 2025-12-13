package in.shubhamprakash681.ecom_micro.user.dto;

import in.shubhamprakash681.ecom_micro.user.enums.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;

    private AddressDto address;
}
