package wiss.m295_lb._95_lb_backend.model;

import jakarta.persistence.*;

/**
 * This is the POJO for the "game" table
 */
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
