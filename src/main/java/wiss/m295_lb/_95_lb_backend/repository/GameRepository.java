package wiss.m295_lb._95_lb_backend.repository;

import org.springframework.data.repository.CrudRepository;
import wiss.m295_lb._95_lb_backend.model.Game;

public interface GameRepository extends CrudRepository<Game, Integer> {
}
