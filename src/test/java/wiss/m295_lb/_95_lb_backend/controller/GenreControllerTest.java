package wiss.m295_lb._95_lb_backend.controller;

import org.junit.jupiter.api.AfterEach;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreControllerTest {
    private static final String A_DESCRIPTION = "a_description";
    private static final String A_NAME = "a_name";
    private final static Long  GENRE_ID = 7L;

    @InjectMocks
    private GenreController testee;

    @Mock
    private GenreRepository genreRepository;
    @Mock
    private Genre genre, savedGenre, existingGenre;
    @Mock
    private List<Genre> genres;

    @AfterEach
    void afterEight() {
        verifyNoMoreInteractions(
                genreRepository,
                genre, savedGenre, existingGenre,
                genres
        );
    }

    @Test
    void getGenreById_found_genre() {
        // arrange
        when(genreRepository.findById(GENRE_ID)).thenReturn(Optional.of(genre));
        // act
        ResponseEntity<Genre> actual = testee.getGenreById(GENRE_ID);
        // assert
        assertThat(actual.getBody()).isEqualTo(genre);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void getGenreById_notFound_notFound() {
        // arrange

        // act
        ResponseEntity<Genre> actual = testee.getGenreById(GENRE_ID);
        // assert
        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(genreRepository).findById(GENRE_ID);
    }


    @Test
    void getAllGenre() {
        // arrange
        when(genreRepository.findAll()).thenReturn(genres);
        // act
        ResponseEntity<List<Genre>> actual = testee.getAllGenre();
        // assert
        assertEquals(genres, actual.getBody());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void createGenre() {
        // arrange
        when(genreRepository.save(genre)).thenReturn(savedGenre);
        // act
        ResponseEntity<Genre> actual = testee.createGenre(genre);
        // assert
        assertEquals(savedGenre, actual.getBody());
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
    }

    @Test
    void updateGenre_found_updated() {
        // arrange
        when(genreRepository.findById(GENRE_ID)).thenReturn(Optional.of(existingGenre));
        when(genre.getName()).thenReturn(A_NAME);
        when(genre.getDescription()).thenReturn(A_DESCRIPTION);
        when(genreRepository.save(existingGenre)).thenReturn(savedGenre);
        // act
        ResponseEntity<Genre> actual = testee.updateGenre(GENRE_ID, genre);
        // assert
        assertThat(actual.getBody()).isEqualTo(savedGenre);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(existingGenre).setName(A_NAME);
        verify(existingGenre).setDescription(A_DESCRIPTION);
    }

    @Test
    void updateGenre_notFound_notFound() {
        // arrange

        // act
        ResponseEntity<Genre> actual = testee.updateGenre(GENRE_ID, genre);
        // assert
        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(genreRepository).findById(GENRE_ID);
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

    @Test
    void deleteGenre_notFound_notFound() {
        // arrange

        // act
        ResponseEntity<Void> actual = testee.deleteGenre(GENRE_ID);
        // assert
        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(genreRepository).existsById(GENRE_ID);
    }
}