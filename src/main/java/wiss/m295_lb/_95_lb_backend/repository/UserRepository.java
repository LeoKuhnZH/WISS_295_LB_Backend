package wiss.m295_lb._95_lb_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wiss.m295_lb._95_lb_backend.model.User;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
