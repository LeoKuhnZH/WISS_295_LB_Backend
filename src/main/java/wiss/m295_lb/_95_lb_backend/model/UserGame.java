package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_game")
public class UserGame {
    @EmbeddedId
    private UserGameId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "score", nullable = false)
    private int score = 0;

    public UserGame() {}

    public UserGame(User user, Game game, int score) {
        this.user = user;
        this.game = game;
        this.score = score;
        this.id = new UserGameId(user.getId(), game.getId());
    }

    public UserGameId getId() {
        return id;
    }

    public void setId(UserGameId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (id != null) id.setUserId(user.getId());
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        if (id != null) id.setGameId(game.getId());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
