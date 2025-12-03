package in.shubhamprakash681.repositories;

import in.shubhamprakash681.models.CartItem;
import in.shubhamprakash681.models.Product;
import in.shubhamprakash681.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);

    void deleteByUser(User user);
}
