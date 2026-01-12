package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

/**
 * This is the POJO for the "genre" table
 */
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private Optional<String> description;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private List<Game> games;

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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
