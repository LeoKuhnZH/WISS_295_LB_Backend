package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserGameKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "game_id")
    Long gameId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

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
