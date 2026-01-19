package wiss.m295_lb._95_lb_backend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wiss.m295_lb._95_lb_backend.model.User;
import wiss.m295_lb._95_lb_backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * The type User controller.
 */
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    /**
     * Instantiates a new User controller.
     *
     * @param userRepository the user repository
     */
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(
            @PathVariable @Min(1)
            Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    /**
     * Create user response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody User user) {
        User createdUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Update user response entity.
     *
     * @param id   the id
     * @param user the user
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody User user
    ) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userToUpdate = existingUser.get();
        userToUpdate.setUsername(user.getUsername());

        User updatedUser = userRepository.save(userToUpdate);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Patch user response entity.
     *
     * @param id   the id
     * @param user the user
     * @return the response entity
     */
    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(
            @PathVariable @Min(1) Long id,
            @RequestBody User user
    ) {
        return userRepository.findById(id)
                .map(existing -> {
                    if (user.getUsername() != null) {
                        existing.setUsername(user.getUsername());
                    }

                    return ResponseEntity.ok(userRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete user response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Min(1) Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
