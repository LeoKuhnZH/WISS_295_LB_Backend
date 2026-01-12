package wiss.m295_lb._95_lb_backend.repository;

import org.springframework.data.repository.CrudRepository;
import wiss.m295_lb._95_lb_backend.model.UserGame;
import wiss.m295_lb._95_lb_backend.model.UserGameId;

public interface UserGameRepository extends CrudRepository<UserGame, UserGameId> {
}
