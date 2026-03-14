package wiss.m295_lb._95_lb_backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wiss.m295_lb._95_lb_backend.repository.GenreRepository;

/**
 * The type Genre controller test.
 */
@ExtendWith(MockitoExtension.class)
class GenreControllerTest {
    @InjectMocks
    private GenreController testee;

    @Mock
    private GenreRepository genreRepository;

    /**
     * Gets genre by id.
     */
    @Test
    void getGenreById() {
        // arrange

        // act

        // assert

    }

    /**
     * Gets all genre.
     */
    @Test
    void getAllGenre() {
        // arrange

        // act

        // assert

    }

    @Test
    void createGenre() {
        // arrange

        // act

        // assert

    }

    @Test
    void updateGenre() {
        // arrange

        // act

        // assert

    }

    @Test
    void patchGenre() {
        // arrange

        // act

        // assert

    }

    @Test
    void deleteGenre() {
        // arrange

        // act

        // assert

    }
}