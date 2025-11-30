package in.shubhamprakash681.dto;

import in.shubhamprakash681.models.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {
    private String id;

    private Product product;
    private Integer quantity;
    private BigDecimal price;
}
