package in.shubhamprakash681.order.services;

import in.shubhamprakash681.order.dto.CartItemResponse;
import in.shubhamprakash681.order.dto.OrderCreatedEvent;
import in.shubhamprakash681.order.dto.OrderItemDTO;
import in.shubhamprakash681.order.dto.OrderResponse;
import in.shubhamprakash681.order.enums.OrderStatus;
import in.shubhamprakash681.order.models.Order;
import in.shubhamprakash681.order.models.OrderItem;
import in.shubhamprakash681.order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Transactional
    public Optional<OrderResponse> createOrder(String userId) {
        // Validate for CartItem
        List<CartItemResponse> cartItemsResponse = cartService.getCartItems(userId);
        if (cartItemsResponse.isEmpty()) {
            return Optional.empty();
        }

        // Calculate total price
        BigDecimal totalPrice = cartItemsResponse.stream().map(CartItemResponse::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create Order and OrderItems
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItemsResponse.stream().map(cartItemResponse -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItemResponse.getProductId());
            orderItem.setQuantity(cartItemResponse.getQuantity());
            orderItem.setPrice(cartItemResponse.getPrice());
            orderItem.setOrder(order);
            return orderItem;
        }).toList();
        order.setItems(orderItems);
        Order createdOrder = orderRepository.save(order);

        // Clear Cart
        boolean cartCleared = cartService.clearCart(userId);
        if (!cartCleared) {
            return Optional.empty();
        }

        OrderCreatedEvent orderEvent = new OrderCreatedEvent(createdOrder.getId(), createdOrder.getUserId(),
                createdOrder.getStatus(),
                createdOrder.getItems().stream().map(this::mapOrderItemToDto).toList(),
                createdOrder.getTotalAmount(), createdOrder.getCreatedAt());

        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderEvent);

        return Optional.of(mapOrderToOrderResponse(createdOrder));
    }

    private OrderResponse mapOrderToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());
        List<OrderItemDTO> items = order.getItems().stream().map(this::mapOrderItemToDto).toList();

        response.setItems(items);

        return response;
    }

    private OrderItemDTO mapOrderItemToDto(OrderItem item) {
        OrderItemDTO itemDTO = new OrderItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setProductId(item.getProductId());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setSubTotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

        return itemDTO;
    }
}
