package in.shubhamprakash681.ecom_micro.order.dto;

import in.shubhamprakash681.ecom_micro.order.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;

    private List<OrderItemDTO> items;

    private LocalDateTime createdAt;
}
