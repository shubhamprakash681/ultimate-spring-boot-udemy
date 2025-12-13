package in.shubhamprakash681.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shubhamprakash681.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}