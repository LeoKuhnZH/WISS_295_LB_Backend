package wiss.m295_lb._95_lb_backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wiss.m295_lb._95_lb_backend.model.Game;
import wiss.m295_lb._95_lb_backend.repository.GameRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = gameRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(
            @PathVariable Long id,
            @Valid @RequestBody Game game
    ) {
        Optional<Game> existingGame = gameRepository.findById(id);
        if (existingGame.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Game gameToUpdate = existingGame.get();
        gameToUpdate.setTitle(game.getTitle());
        if (game.getDescription().isPresent()) {
            gameToUpdate.setDescription(game.getDescription().get());
        } else {
            gameToUpdate.setDescription(null);
        }
        gameToUpdate.setRating(game.getRating());
        gameToUpdate.setGenres(game.getGenres());

        Game updatedGame = gameRepository.save(gameToUpdate);
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            gameRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
