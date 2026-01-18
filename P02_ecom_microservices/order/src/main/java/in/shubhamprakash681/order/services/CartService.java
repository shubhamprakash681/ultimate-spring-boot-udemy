package in.shubhamprakash681.order.services;

import in.shubhamprakash681.order.clients.products.ProductServiceClient;
import in.shubhamprakash681.order.clients.users.UserServiceClient;
import in.shubhamprakash681.order.dto.CartItemRequest;
import in.shubhamprakash681.order.dto.CartItemResponse;
import in.shubhamprakash681.order.dto.product.ProductResponse;
import in.shubhamprakash681.order.dto.user.UserResponse;
import in.shubhamprakash681.order.models.CartItem;
import in.shubhamprakash681.order.repositories.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ProductServiceClient productServiceClient;
    private final UserServiceClient userServiceClient;

    public boolean addToCart(String userId, CartItemRequest item) {
        ProductResponse productRes = productServiceClient.getProductById(item.getProductId());
        UserResponse userResponse = userServiceClient.getUserDetails(Long.parseLong(userId));

        if (productRes == null || productRes.getId() == null || userResponse == null || userResponse.getId() == null) return false;

        if (productRes.getStockQuantity() < item.getQuantity()) return false;

        CartItem existingCartItem = cartRepository.findByUserIdAndProductId(userId, item.getProductId());
        if (existingCartItem != null) {
            // Update quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
            existingCartItem.setPrice(item.getProductPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartRepository.save(existingCartItem);
        } else {
            // Create a new cart item
            CartItem cartItem = new CartItem();
            cartItem.setProductId(item.getProductId());
            cartItem.setUserId(userId);
            cartItem.setQuantity(item.getQuantity());
            cartItem.setPrice(item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            cartRepository.save(cartItem);
        }

        return true;
    }

    public boolean deleteItemFromCart(String userId, String productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);

        return true;
    }

    public List<CartItemResponse> getCartItems(String userId) {
        return cartRepository.findByUserId(userId).stream().map(this::mapCartItemToCartItemResponse).toList();
    }

    public boolean clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
        return true;
    }

    private CartItemResponse mapCartItemToCartItemResponse(CartItem cartItem) {
        CartItemResponse cartItemResponse = new CartItemResponse();
        cartItemResponse.setId(String.valueOf(cartItem.getId()));
        cartItemResponse.setProductId(cartItem.getProductId());
        cartItemResponse.setQuantity(cartItem.getQuantity());
        cartItemResponse.setPrice(cartItem.getPrice());

        return cartItemResponse;
    }
}
