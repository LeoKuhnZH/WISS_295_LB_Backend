package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * The type Genre.
 */
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @ManyToMany(mappedBy = "genres")
    private Set<Game> games;

    /**
     * Gets genre id.
     *
     * @return the genre id
     */
    public Long getGenreId() {
        return genreId;
    }

    /**
     * Sets genre id.
     *
     * @param genreId the genre id
     */
    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets games.
     *
     * @return the games
     */
    public Set<Game> getGames() {
        return games;
    }

    /**
     * Sets games.
     *
     * @param games the games
     */
    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
