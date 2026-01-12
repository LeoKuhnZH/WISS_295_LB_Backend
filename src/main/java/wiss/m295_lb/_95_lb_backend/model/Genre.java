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
}
