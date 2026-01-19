package wiss.m295_lb._95_lb_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wiss.m295_lb._95_lb_backend.repository.GenreRepository;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
}
