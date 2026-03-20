package wiss.m295_lb._95_lb_backend.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wiss.m295_lb._95_lb_backend.model.User;
import wiss.m295_lb._95_lb_backend.repository.UserRepository;

import java.util.List;

import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController testee;

    @Mock
    private UserRepository userRepository;
    @Mock
    private User user, savedUser, existingUser;
    @Mock
    private List<User> users;

    @AfterEach
    void afterEight() {
        verifyNoMoreInteractions(
                userRepository,
                user,savedUser,existingUser,
                users
        );
    }

    @Test
    void findById_found_user() {
        // arrange

        // act

        // assert

    }

    @Test
    void findById_notFound_notFound() {
        // arrange

        // act

        // assert

    }

    @Test
    void getAllUsers() {
        // arrange

        // act

        // assert

    }

    @Test
    void createUser() {
        // arrange

        // act

        // assert

    }

    @Test
    void updateUser() {
        // arrange

        // act

        // assert

    }

    @Test
    void patchUser() {
        // arrange

        // act

        // assert

    }

    @Test
    void deleteUser() {
        // arrange

        // act

        // assert

    }
}