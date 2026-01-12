package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.*;

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

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = true)
    private Optional<String> description;

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
}
