package wiss.m295_lb._95_lb_backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wiss.m295_lb._95_lb_backend.model.Genre;
import wiss.m295_lb._95_lb_backend.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreControllerTest {
    private final static Long  GENRE_ID = 7L;

    @InjectMocks
    private GenreController testee;

    @Mock
    private GenreRepository genreRepository;
    @Mock
    private Genre genre;
    @Mock
    private List<Genre> genres;

    @Test
    void getGenreById_found_genre() {
        // arrange
        when(genreRepository.findById(GENRE_ID)).thenReturn(Optional.of(genre));
        // act
        ResponseEntity<Genre> actual = testee.getGenreById(GENRE_ID);
        // assert
        assertThat(actual.getBody()).isEqualTo(genre);
    }

    @Test
    void getGenreById_notFound_exception() {
        // arrange

        // act
        ResponseEntity<Genre> actual = testee.getGenreById(GENRE_ID);
        // assert
        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    void getAllGenre() {
        // arrange
        when(genreRepository.findAll()).thenReturn(genres);
        // act
        ResponseEntity<List<Genre>> actual = testee.getAllGenre();
        // assert
        assertEquals(genres, actual.getBody());
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