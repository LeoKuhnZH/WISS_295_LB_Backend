package wiss.m295_lb._95_lb_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    @JsonBackReference
    private Genre genre;

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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
