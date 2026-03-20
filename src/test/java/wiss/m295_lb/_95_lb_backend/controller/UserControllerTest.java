package wiss.m295_lb._95_lb_backend.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wiss.m295_lb._95_lb_backend.model.User;
import wiss.m295_lb._95_lb_backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    public static final long USER_ID = 7L;
    public static final String A_USERNAME = "a_username";

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
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        // act
        ResponseEntity<User> actual = testee.findById(USER_ID);
        // assert
        assertThat(actual.getBody()).isEqualTo(user);
        assertEquals(HttpStatus.OK, actual.getStatusCode());

    }

    @Test
    void findById_notFound_notFound() {
        // arrange

        // act
        ResponseEntity<User> actual = testee.findById(USER_ID);
        // assert
        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(userRepository).findById(USER_ID);


    }

    @Test
    void getAllUsers() {
        // arrange
        when(userRepository.findAll()).thenReturn(users);
        // act
        ResponseEntity<List<User>> actual = testee.getAllUsers();
        // assert
        assertThat(actual.getBody()).isEqualTo(users);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void createUser() {
        // arrange
        when(userRepository.save(user)).thenReturn(savedUser);
        // act
        ResponseEntity<User> actual = testee.createUser(user);
        // assert
        assertThat(actual.getBody()).isEqualTo(savedUser);
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
    }

    @Test
    void updateUser_found_updated() {
        // arrange
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(existingUser));
        when(user.getUsername()).thenReturn(A_USERNAME);
        when(userRepository.save(existingUser)).thenReturn(savedUser);
        // act
        ResponseEntity<User> actual = testee.updateUser(USER_ID, user);
        // assert
        assertThat(actual.getBody()).isEqualTo(savedUser);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(existingUser).setUsername(A_USERNAME);
    }

    @Test
    void updateUser_notFound_notFound() {
        // arrange

        // act
        ResponseEntity<User> actual = testee.updateUser(USER_ID, user);
        // assert
        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(userRepository).findById(USER_ID);
    }

    @Test
    void patchUser() {
        // arrange

        // act

        // assert

    }

    @Test
    void deleteUser_found_deleted() {
        // arrange
        when(userRepository.existsById(USER_ID)).thenReturn(true);
        // act
        ResponseEntity<Void> actual = testee.deleteUser(USER_ID);
        // assert
        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        verify(userRepository).deleteById(USER_ID);
    }

    @Test
    void deleteUser_notFound_notFound() {
        // arrange

        // act
        ResponseEntity<Void> actual = testee.deleteUser(USER_ID);
        // assert
        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(userRepository).existsById(USER_ID);
    }
}