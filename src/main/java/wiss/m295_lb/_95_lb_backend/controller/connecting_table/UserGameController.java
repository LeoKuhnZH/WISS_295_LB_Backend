package wiss.m295_lb._95_lb_backend.controller.connecting_table;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wiss.m295_lb._95_lb_backend.model.Game;
import wiss.m295_lb._95_lb_backend.model.User;
import wiss.m295_lb._95_lb_backend.model.connecting_table.UserGame;
import wiss.m295_lb._95_lb_backend.model.connecting_table.key.UserGameKey;
import wiss.m295_lb._95_lb_backend.repository.GameRepository;
import wiss.m295_lb._95_lb_backend.repository.UserRepository;
import wiss.m295_lb._95_lb_backend.repository.connecting_table.UserGameRepository;

import java.util.List;
import java.util.Optional;

/**
 * <p>The type User game controller.</p>
 */
@Validated
@RestController
@RequestMapping("/api/user-game")
public class UserGameController {

    private final UserGameRepository userGameRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    /**
     * <p>Instantiates a new User game controller.</p>
     *
     * @param userGameRepository the user game repository
     * @param userRepository     the user repository
     * @param gameRepository     the game repository
     */
    public UserGameController(
            UserGameRepository userGameRepository,
            UserRepository userRepository,
            GameRepository gameRepository
    ) {
        this.userGameRepository = userGameRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    /**
     * <p>Defines the shape of a Request.</p>
     */
    public record UserGameRequest(
            @Min(1) Long userId,
            @Min(1) Long gameId,
            @Min(0) Integer score
    ) {}

    /**
     * <p>Defines the shape of a Response.</p>
     */
    public record UserGameResponse(Long userId, Long gameId, Integer score) {}

    /**
     * <p>makes the model userGame into a response</p>
     *
     * @param userGame
     * @return
     */

    private static UserGameResponse toResponse(UserGame userGame) {
        return new UserGameResponse(
                userGame.getUser().getUserId(),
                userGame.getGame().getGameId(),
                userGame.getScore()
        );
    }

    /**
     * <p>Returns you an userGame (properly formatted) as a response defined by both id's.</p>
     *
     * @param userId the user id
     * @param gameId the game id
     * @return the one
     */
    @GetMapping("/{userId}/{gameId}")
    public ResponseEntity<UserGameResponse> getOne(
            @PathVariable @Min(1) Long userId,
            @PathVariable @Min(1) Long gameId
    ) {
        return userGameRepository.findByUser_UserIdAndGame_GameId(userId, gameId)
                .map(userGame -> ResponseEntity.ok(toResponse(userGame)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * <p>Returns you a List of all the relationships the user with the id has.</p>
     *
     * @param userId the user id
     * @return the by user
     */
    @PatchMapping("/by-user/{userId}")
    public ResponseEntity<List<UserGameResponse>> getByUser(@PathVariable @Min(1) Long userId) {
        List<UserGameResponse> result = userGameRepository.findByUser_UserId(userId)
                .stream()
                .map(UserGameController::toResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    /**
     * <p>Returns you a List of all the relationships the game with the id has.</p>
     *
     * @param gameId the game id
     * @return the by game
     */
    @GetMapping("/by-game/{gameId}")
    public ResponseEntity<List<UserGameResponse>> getByGame(@PathVariable @Min(1) Long gameId) {
        List<UserGameResponse> result = userGameRepository.findByGame_GameId(gameId)
                .stream()
                .map(UserGameController::toResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    /**
     * <p>Creates a new Relationship of User and Game</p>
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<UserGameResponse> create(@Valid @RequestBody UserGameRequest request) {
        Optional<User> userOpt = userRepository.findById(request.userId());
        Optional<Game> gameOpt = gameRepository.findById(request.gameId());

        if (userOpt.isEmpty() || gameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserGameKey key = new UserGameKey();
        key.setUserId(request.userId());
        key.setGameId(request.gameId());

        if (userGameRepository.existsById(key)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        UserGame userGame = new UserGame();
        userGame.setId(key);
        userGame.setUser(userOpt.get());
        userGame.setGame(gameOpt.get());
        userGame.setScore(request.score() == null ? 0 : request.score());

        UserGame created = userGameRepository.save(userGame);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
    }

    /**
     * <p>Update score in a defined UserGame relationship.</p>
     *
     * @param userId  the user id
     * @param gameId  the game id
     * @param request the request
     * @return the response entity
     */
    @PutMapping("/{userId}/{gameId}")
    public ResponseEntity<UserGameResponse> updateScore(
            @PathVariable @Min(1) Long userId,
            @PathVariable @Min(1) Long gameId,
            @Valid @RequestBody UserGameRequest request
    ) {
        return userGameRepository.findByUser_UserIdAndGame_GameId(userId, gameId)
                .map(existing -> {
                    if (request.score() != null) {
                        existing.setScore(request.score());
                    }
                    UserGame saved = userGameRepository.save(existing);
                    return ResponseEntity.ok(toResponse(saved));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * <p>deletes the UserGame relationship</p>
     *
     * @param userId the user id
     * @param gameId the game id
     * @return the response entity
     */
    @DeleteMapping("/{userId}/{gameId}")
    public ResponseEntity<Void> delete(
            @PathVariable @Min(1) Long userId,
            @PathVariable @Min(1) Long gameId
    ) {
        UserGameKey key = new UserGameKey();
        key.setUserId(userId);
        key.setGameId(gameId);

        if (!userGameRepository.existsById(key)) {
            return ResponseEntity.notFound().build();
        }

        userGameRepository.deleteById(key);
        return ResponseEntity.noContent().build();
    }
}
