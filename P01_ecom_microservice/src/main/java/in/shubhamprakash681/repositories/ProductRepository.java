package in.shubhamprakash681.repositories;

import in.shubhamprakash681.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIsActiveTrue();

    @Query("SELECT p FROM products_table p WHERE p.isActive=true AND p.stockQuantity > 0 AND LOWER(p.name) LIKE LOWER(CONCAT" +
            "('%', :keyword ,'%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);
}
