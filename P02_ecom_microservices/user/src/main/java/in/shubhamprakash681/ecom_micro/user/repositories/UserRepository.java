package in.shubhamprakash681.ecom_micro.user.repositories;

import in.shubhamprakash681.ecom_micro.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}