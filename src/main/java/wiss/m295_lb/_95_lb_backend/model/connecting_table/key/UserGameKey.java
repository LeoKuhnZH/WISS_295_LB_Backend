package wiss.m295_lb._95_lb_backend.model.connecting_table.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type User game key.
 */
@Embeddable
public class UserGameKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "game_id")
    private Long gameId;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets game id.
     *
     * @return the game id
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * Sets game id.
     *
     * @param gameId the game id
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserGameKey that = (UserGameKey) o;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getGameId(), that.getGameId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getGameId());
    }
}
