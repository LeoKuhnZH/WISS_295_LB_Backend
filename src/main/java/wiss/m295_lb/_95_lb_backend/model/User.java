package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.*;
import wiss.m295_lb._95_lb_backend.model.connecting_table.UserGame;

import java.util.Set;

/**
 * This is the POJO for the "user" table
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserGame> getGames() {
        return games;
    }

    public void setGames(Set<UserGame> games) {
        this.games = games;
    }
}
