package wiss.m295_lb._95_lb_backend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wiss.m295_lb._95_lb_backend.model.Genre;
import wiss.m295_lb._95_lb_backend.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable @Min(1) Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenre() {
        return ResponseEntity.ok(genreRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(
            @Valid @RequestBody Genre genre) {
        Genre newGenre = genreRepository.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody Genre genre) {
        Optional<Genre> existingGenre = genreRepository.findById(id);
        if (existingGenre.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Genre genreToUpdate = existingGenre.get();
        genreToUpdate.setName(genre.getName());
        genreToUpdate.setDescription(genre.getDescription());
        Genre updatedGenre = genreRepository.save(genreToUpdate);
        return ResponseEntity.ok(updatedGenre);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Genre> patchGenre(
            @PathVariable @Min(1) Long id,
            @RequestBody Genre genre
    ) {
        return genreRepository.findById(id)
                .map(existing -> {

                    if (genre.getName() != null) {
                        existing.setName(genre.getName());
                    }

                    if (genre.getDescription() != null) {
                        existing.setDescription(genre.getDescription());
                    }

                    return ResponseEntity.ok(genreRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable @Min(1) Long id) {
        if (!genreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        genreRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
