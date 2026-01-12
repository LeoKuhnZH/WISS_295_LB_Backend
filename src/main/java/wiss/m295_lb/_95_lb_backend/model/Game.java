package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

/**
 * This is the POJO for the "game" table
 */
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private Optional<String> description;

    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "game_genre",
               joinColumns = @JoinColumn(name = "game_id"),
               inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<UserGame> userGames;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description.orElse("");
    }

    public void setDescription(String description) {
        this.description = description == null ? null : Optional.of(description);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<UserGame> getUserGames() {
        return userGames;
    }

    public void setUserGames(List<UserGame> userGames) {
        this.userGames = userGames;
    }
}
