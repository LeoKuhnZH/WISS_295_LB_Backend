package wiss.m295_lb._95_lb_backend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wiss.m295_lb._95_lb_backend.model.Game;
import wiss.m295_lb._95_lb_backend.repository.GameRepository;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable @Min(1) Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(
            @Valid @RequestBody Game game) {
        Game createdGame = gameRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody Game game
    ) {
        Optional<Game> existingGame = gameRepository.findById(id);
        if (existingGame.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Game gameToUpdate = existingGame.get();
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setDescription(game.getDescription());
        gameToUpdate.setRating(game.getRating());
        gameToUpdate.setGenres(game.getGenres());

        Game updatedGame = gameRepository.save(gameToUpdate);
        return ResponseEntity.ok(updatedGame);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Game> patchGame(
            @PathVariable @Min(1) Long id,
            @RequestBody Game game
    )
        {
        return gameRepository.findById(id)
                .map(existing -> {

                    if (game.getTitle() != null) {
                        existing.setTitle(game.getTitle());
                    }

                    if (game.getDescription() != null) {
                        existing.setDescription(game.getDescription());
                    }

                    if (game.getRating() != null) {
                        existing.setRating(game.getRating());
                    }

                    if (game.getGenres() != null) {
                        existing.setGenres(game.getGenres());
                    }

                    return ResponseEntity.ok(gameRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable @Min(1) Long id) {
        if (!gameRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
