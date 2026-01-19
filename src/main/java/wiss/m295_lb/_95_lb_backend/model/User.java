package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.*;
import wiss.m295_lb._95_lb_backend.model.connecting_table.UserGame;

import java.util.Set;

/**
 * The type User.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<UserGame> games;

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
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets games.
     *
     * @return the games
     */
    public Set<UserGame> getGames() {
        return games;
    }

    /**
     * Sets games.
     *
     * @param games the games
     */
    public void setGames(Set<UserGame> games) {
        this.games = games;
    }
}
