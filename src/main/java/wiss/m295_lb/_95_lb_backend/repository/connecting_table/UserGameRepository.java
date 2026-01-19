package wiss.m295_lb._95_lb_backend.repository.connecting_table;

import org.springframework.data.jpa.repository.JpaRepository;
import wiss.m295_lb._95_lb_backend.model.connecting_table.UserGame;
import wiss.m295_lb._95_lb_backend.model.connecting_table.key.UserGameKey;

import java.util.List;
import java.util.Optional;

public interface UserGameRepository extends JpaRepository<UserGame, UserGameKey> {
    List<UserGame> findByUser_UserId(Long id);
    List<UserGame> findByGame_GameId(Long id);
    Optional<UserGame> findByUser_UserIdAndGame_GameId(Long id, Long gameId);
}
