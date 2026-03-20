package wiss.m295_lb._95_lb_backend.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wiss.m295_lb._95_lb_backend.model.Game;
import wiss.m295_lb._95_lb_backend.model.Genre;
import wiss.m295_lb._95_lb_backend.repository.GameRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {
    private static final String A_DESCRIPTION = "a_description";
    private static final String A_TITLE = "a_name";
    private static final Integer A_RATING = 2;
    public static final long GAME_ID = 7L;

    @InjectMocks
    private GameController testee;

    @Mock
    private GameRepository gameRepository;
    @Mock
    private Game game, savedGame, existingGame;
    @Mock
    private List<Game> games;
    @Mock
    private Set<Genre> genres;


    @AfterEach
    void afterEight() {
        verifyNoMoreInteractions(
                gameRepository,
                game,savedGame,existingGame,
                games,
                genres
        );
    }

    @Test
    void getGame_found_game() {
        // arrange
        when(gameRepository.findById(GAME_ID)).thenReturn(Optional.of(game));
        // act
        ResponseEntity<Game> actual = testee.getGame(GAME_ID);
        // assert
        assertThat(actual.getBody()).isEqualTo(game);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void getGame_notFound_notFound() {
        // arrange

        // act
        ResponseEntity<Game> actual = testee.getGame(GAME_ID);
        // assert
        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(gameRepository).findById(GAME_ID);
    }

    @Test
    void getAllGames() {
        // arrange
        when(gameRepository.findAll()).thenReturn(games);
        // act
        ResponseEntity<List<Game>> actual = testee.getAllGames();
        // assert
        assertEquals(games,actual.getBody());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void createGame() {
        // arrange
        when(gameRepository.save(game)).thenReturn(savedGame);
        // act
        ResponseEntity<Game> actual = testee.createGame(game);
        // assert
        assertEquals(savedGame,actual.getBody());
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
    }

    @Test
    void updateGame_found_updated() {
        // arrange
        when(gameRepository.findById(GAME_ID)).thenReturn(Optional.of(existingGame));
        when(game.getTitle()).thenReturn(A_TITLE);
        when(game.getDescription()).thenReturn(A_DESCRIPTION);
        when(game.getRating()).thenReturn(A_RATING);
        when(game.getGenres()).thenReturn(genres);
        when(gameRepository.save(existingGame)).thenReturn(savedGame);
        // act
        ResponseEntity<Game> actual = testee.updateGame(GAME_ID, game);
        // assert
        assertThat(actual.getBody()).isEqualTo(savedGame);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(existingGame).setTitle(A_TITLE);
        verify(existingGame).setDescription(A_DESCRIPTION);
        verify(existingGame).setRating(A_RATING);
        verify(existingGame).setGenres(genres);
    }

    @Test
    void updateGame_notFound_notFound() {
        // arrange

        // act
        ResponseEntity<Game> actual = testee.updateGame(GAME_ID, game);
        // assert
        assertThat(actual.getBody()).isNull();
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
        verify(gameRepository).findById(GAME_ID);
    }

    @Test
    void patchGame() {
        // arrange

        // act

        // assert

    }

    @Test
    void deleteGame_found_deleted() {
        // arrange
        when(gameRepository.existsById(GAME_ID)).thenReturn(true);
        // act
        ResponseEntity<Void> actual = testee.deleteGame(GAME_ID);
        // assert
        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        verify(gameRepository).deleteById(GAME_ID);
    }

    @Test
    void deleteGame_notFound_notFound() {
        // arrange

        // act
        ResponseEntity<Void> actual = testee.deleteGame(GAME_ID);
        // assert
        assertThat(actual.getBody()).isNull();
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
        verify(gameRepository).existsById(GAME_ID);
    }
}