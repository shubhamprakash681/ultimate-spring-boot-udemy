package in.shubhamprakash681.product.repositories;

import in.shubhamprakash681.product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByIsActiveTrue(Pageable pageable);

    @Query("""
        SELECT p from products_table p where p.isActive=true AND p.stockQuantity > 0
        AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Product> searchProducts(@Param("keyword") String keyword);

    Optional<Product> findByIdAndIsActiveTrue(Long id);
}
