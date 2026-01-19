package wiss.m295_lb._95_lb_backend.repository.connecting_table;

import org.springframework.data.jpa.repository.JpaRepository;
import wiss.m295_lb._95_lb_backend.model.connecting_table.UserGame;
import wiss.m295_lb._95_lb_backend.model.connecting_table.key.UserGameKey;

public interface UserGameRepository extends JpaRepository<UserGame, UserGameKey> {
}
