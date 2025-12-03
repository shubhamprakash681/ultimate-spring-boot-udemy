package in.shubhamprakash681.services;

import in.shubhamprakash681.dto.CartItemRequest;
import in.shubhamprakash681.dto.CartItemResponse;
import in.shubhamprakash681.models.CartItem;
import in.shubhamprakash681.models.Product;
import in.shubhamprakash681.models.User;
import in.shubhamprakash681.repositories.CartRepository;
import in.shubhamprakash681.repositories.ProductRepository;
import in.shubhamprakash681.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemRequest item) {
        Optional<Product> productOpt = productRepository.findById(item.getProductId());
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        if (productOpt.isEmpty() || userOpt.isEmpty()) return false;

        Product product = productOpt.get();
        User user = userOpt.get();

        if (product.getStockQuantity() < item.getQuantity()) return false;

        CartItem existingCartItem = cartRepository.findByUserAndProduct(user, product);
        if (existingCartItem != null) {
            // Update quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartRepository.save(existingCartItem);
        } else {
            // Create a new cart item
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setUser(user);
            cartItem.setQuantity(item.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            cartRepository.save(cartItem);
        }

        return true;
    }

    public boolean deleteItemFromCart(String userId, long productId) {
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isEmpty() || userOpt.isEmpty()) return false;

        Product product = productOpt.get();
        User user = userOpt.get();

        cartRepository.deleteByUserAndProduct(user, product);

        return true;
    }

    public List<CartItemResponse> getCartItems(String userId) {
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        return userOpt.map(user -> cartRepository.findByUser(user).stream().map(this::mapCartItemToCartItemResponse).toList()).orElseGet(ArrayList::new);
    }

    public boolean clearCart(String userId) {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));

        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        cartRepository.deleteByUser(user);
        return true;
    }

    private CartItemResponse mapCartItemToCartItemResponse(CartItem cartItem) {
        CartItemResponse cartItemResponse = new CartItemResponse();
        cartItemResponse.setId(String.valueOf(cartItem.getId()));
        cartItemResponse.setProduct(cartItem.getProduct());
        cartItemResponse.setQuantity(cartItem.getQuantity());
        cartItemResponse.setPrice(cartItem.getPrice());

        return cartItemResponse;
    }
}
