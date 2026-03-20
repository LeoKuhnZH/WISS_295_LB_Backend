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
import wiss.m295_lb._95_lb_backend.repository.GameRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {
    public static final long GAME_ID = 7L;
    @InjectMocks
    private GameController testee;

    @Mock
    private GameRepository gameRepository;
    @Mock
    private Game game, savedGame;
    @Mock
    private List<Game> games;

    @AfterEach
    void afterEight() {
        verifyNoMoreInteractions(
                gameRepository
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

        // act

        // assert

    }

    @Test
    void updateGame() {
        // arrange

        // act

        // assert

    }

    @Test
    void patchGame() {
        // arrange

        // act

        // assert

    }

    @Test
    void deleteGame() {
        // arrange

        // act

        // assert

    }
}