package wiss.m295_lb._95_lb_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wiss.m295_lb._95_lb_backend.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
