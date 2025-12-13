package in.shubhamprakash681.ecom_micro.order.repositories;

import in.shubhamprakash681.ecom_micro.order.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserIdAndProductId(String userId, String productId);

    void deleteByUserIdAndProductId(String userId, String productId);

    List<CartItem> findByUserId(String userId);

    void deleteByUserId(String userId);
}
