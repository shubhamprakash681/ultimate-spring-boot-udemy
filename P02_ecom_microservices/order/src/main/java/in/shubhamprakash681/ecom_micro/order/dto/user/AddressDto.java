package in.shubhamprakash681.ecom_micro.order.dto.user;

import lombok.Data;

@Data
public class AddressDto {
    private String id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
