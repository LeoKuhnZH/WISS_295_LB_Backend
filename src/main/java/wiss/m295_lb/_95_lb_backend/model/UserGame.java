package wiss.m295_lb._95_lb_backend.model;


import jakarta.persistence.*;

/**
 * Connection table between User and Game
 */
@Entity
@Table(name="user_game")
public class UserGame {


    @Column(name = "score", nullable = false)
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        try {
            this.score = score;
        } catch (NullPointerException e) {
            this.score = 0;
        }
    }
}
