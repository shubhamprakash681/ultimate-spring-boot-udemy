package in.shubhamprakash681.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemRequest {
    private String productId;
    private BigDecimal productPrice;
    private Integer quantity;
}
