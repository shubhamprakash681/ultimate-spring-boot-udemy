package in.shubhamprakash681.ecom_micro.order.repositories;

import in.shubhamprakash681.ecom_micro.order.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
