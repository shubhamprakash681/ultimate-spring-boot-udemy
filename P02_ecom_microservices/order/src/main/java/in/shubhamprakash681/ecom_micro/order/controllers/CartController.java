package in.shubhamprakash681.ecom_micro.order.controllers;

import in.shubhamprakash681.ecom_micro.order.dto.CartItemRequest;
import in.shubhamprakash681.ecom_micro.order.dto.CartItemResponse;
import in.shubhamprakash681.ecom_micro.order.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId,
                                            @RequestBody CartItemRequest item) {
        boolean added = cartService.addToCart(userId, item);

        if (!added) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product out of stock or User not found");

        return ResponseEntity.status(HttpStatus.CREATED).body("Product added to cart");
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<String> deleteCartItem(@RequestHeader("X-User-ID") String userId,
                                                 @PathVariable String productId) {
        boolean isDeleted = cartService.deleteItemFromCart(userId, productId);

        if (!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted from cart");
    }

    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getCartItems(@RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartItems(userId));
    }
}
