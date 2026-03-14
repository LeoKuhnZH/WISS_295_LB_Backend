package wiss.m295_lb._95_lb_backend.controller.connecting_table;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wiss.m295_lb._95_lb_backend.repository.GameRepository;
import wiss.m295_lb._95_lb_backend.repository.UserRepository;
import wiss.m295_lb._95_lb_backend.repository.connecting_table.UserGameRepository;

@ExtendWith(MockitoExtension.class)
class UserGameControllerTest {
    @InjectMocks
    private UserGameController testee;

    @Mock
    private UserGameRepository userGameRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private GameRepository gameRepository;

    @Test
    void getOne() {
        // arrange

        // act

        // assert

    }

    @Test
    void getByUser() {
        // arrange

        // act

        // assert

    }

    @Test
    void getByGame() {
        // arrange

        // act

        // assert

    }

    @Test
    void create() {
        // arrange

        // act

        // assert

    }

    @Test
    void updateScore() {
        // arrange

        // act

        // assert

    }

    @Test
    void delete() {
        // arrange

        // act

        // assert

    }
}