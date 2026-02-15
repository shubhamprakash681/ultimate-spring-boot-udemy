package in.shubhamprakash681.services;

import in.shubhamprakash681.dto.CartItemResponse;
import in.shubhamprakash681.dto.OrderItemDTO;
import in.shubhamprakash681.dto.OrderResponse;
import in.shubhamprakash681.enums.OrderStatus;
import in.shubhamprakash681.models.Order;
import in.shubhamprakash681.models.OrderItem;
import in.shubhamprakash681.models.User;
import in.shubhamprakash681.repositories.OrderRepository;
import in.shubhamprakash681.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartService cartService;

    @Transactional
    public Optional<OrderResponse> createOrder(String userId) {
        // Validate for CartItem
        List<CartItemResponse> cartItemsResponse = cartService.getCartItems(userId);
        if (cartItemsResponse.isEmpty()) {
            return Optional.empty();
        }

        // Validate for user
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }

        User user = userOpt.get();

        // Calculate total price
        BigDecimal totalPrice = cartItemsResponse.stream().map(CartItemResponse::getPrice).reduce(BigDecimal.ZERO,
                BigDecimal::add);

        // Create Order and OrderItems
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItemsResponse.stream().map(cartItemResponse -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItemResponse.getProduct());
            orderItem.setQuantity(cartItemResponse.getQuantity());
            orderItem.setPrice(cartItemResponse.getPrice());
            orderItem.setOrder(order);
            return orderItem;
        }).toList();
        order.setItems(orderItems);
        orderRepository.save(order);

        // Clear Cart
        boolean cartCleared = cartService.clearCart(userId);
        if (!cartCleared) {
            return Optional.empty();
        }

        return Optional.of(mapOrderToOrderResponse(order));
    }

    private OrderResponse mapOrderToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());
        List<OrderItemDTO> items = order.getItems().stream().map(orderItem -> {
            OrderItemDTO itemDTO = new OrderItemDTO();

            itemDTO.setId(orderItem.getId());
            itemDTO.setProductId(orderItem.getProduct().getId());
            itemDTO.setQuantity(orderItem.getQuantity());
            itemDTO.setPrice(orderItem.getPrice());
            itemDTO.setSubTotal(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
            return itemDTO;
        }).toList();

        response.setItems(items);

        return response;
    }
}
