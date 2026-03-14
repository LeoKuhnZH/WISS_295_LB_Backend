package wiss.m295_lb._95_lb_backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wiss.m295_lb._95_lb_backend.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController testee;

    @Mock
    private UserRepository userRepository;

    @Test
    void findById() {
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