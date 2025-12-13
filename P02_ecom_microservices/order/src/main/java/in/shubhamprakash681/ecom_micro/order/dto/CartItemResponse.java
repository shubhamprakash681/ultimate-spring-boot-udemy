package in.shubhamprakash681.ecom_micro.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {
    private String id;

    private String productId;
    private Integer quantity;
    private BigDecimal price;
}
