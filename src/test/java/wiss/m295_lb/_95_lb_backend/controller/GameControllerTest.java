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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {
    public static final long GAME_ID = 7L;
    @InjectMocks
    private GameController testee;

    @Mock
    private GameRepository gameRepository;
    @Mock
    private Game game;

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
    void getAllGames() {
        // arrange

        // act

        // assert

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