package wiss.m295_lb._95_lb_backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Connection table between User and Game
 */
@Entity
@Table(name = "user_game")
public class UserGame {
    private Integer id;

    @Column(name = "score", nullable = false)
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        try {
            this.score = score;
        } catch (NullPointerException e) {
            this.score = 0;
        }
    }
}
