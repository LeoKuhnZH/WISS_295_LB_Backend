package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.*;
import wiss.m295_lb._95_lb_backend.model.connecting_table.UserGame;

import java.util.Set;

/**
 * The type Game.
 */
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", nullable = false)
    private Long gameId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @OneToMany(mappedBy = "game", orphanRemoval = true)
    private Set<UserGame> users;

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

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Gets genres.
     *
     * @return the genres
     */
    public Set<Genre> getGenres() {
        return genres;
    }

    /**
     * Sets genres.
     *
     * @param genres the genres
     */
    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public Set<UserGame> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(Set<UserGame> users) {
        this.users = users;
    }
}
