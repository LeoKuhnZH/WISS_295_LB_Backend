package wiss.m295_lb._95_lb_backend.repository.connecting_table;

import org.springframework.data.jpa.repository.JpaRepository;
import wiss.m295_lb._95_lb_backend.model.connecting_table.UserGame;
import wiss.m295_lb._95_lb_backend.model.connecting_table.key.UserGameKey;

import java.util.List;
import java.util.Optional;

/**
 * The interface User game repository.
 */
public interface UserGameRepository extends JpaRepository<UserGame, UserGameKey> {
    /**
     * Find by user user id list.
     *
     * @param id the id
     * @return the list
     */
    List<UserGame> findByUser_UserId(Long id);

    /**
     * Find by game game id list.
     *
     * @param id the id
     * @return the list
     */
    List<UserGame> findByGame_GameId(Long id);

    /**
     * Find by user user id and game game id optional.
     *
     * @param id     the id
     * @param gameId the game id
     * @return the optional
     */
    Optional<UserGame> findByUser_UserIdAndGame_GameId(Long id, Long gameId);
}
