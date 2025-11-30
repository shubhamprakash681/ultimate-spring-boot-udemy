package in.shubhamprakash681.dto;

import in.shubhamprakash681.models.Product;
import in.shubhamprakash681.models.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemRequest {
    private Long productId;
    private Integer quantity;
}
