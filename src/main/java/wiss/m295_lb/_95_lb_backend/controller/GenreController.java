package wiss.m295_lb._95_lb_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wiss.m295_lb._95_lb_backend.model.Genre;
import wiss.m295_lb._95_lb_backend.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenre() {
        return ResponseEntity.ok(genreRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre newGenre = genreRepository.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGenre);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        Optional<Genre> existingGenre = genreRepository.findById(id);
        if(existingGenre.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Genre genreToUpdate = existingGenre.get();
        genreToUpdate.setName(genre.getName());
        if (genre.getDescription().isPresent()) {
            genreToUpdate.setDescription(genre.getDescription().get());
        }  else {
            genreToUpdate.setDescription(null);
        }
        Genre updatedGenre = genreRepository.save(genreToUpdate);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if(genre.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            genreRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
