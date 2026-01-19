package wiss.m295_lb._95_lb_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wiss.m295_lb._95_lb_backend.repository.GameRepository;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
