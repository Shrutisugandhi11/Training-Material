package com.epam.service;

import com.epam.exception.InvalidCredentialsException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationServiceTest {

    @InjectMocks
    UserAuthenticationService userAuthenticationService;

    @Mock
    UserRepository userRepository;

    User user1;
    User user2;

    @BeforeEach
    void setup() {
        user1 = new User("Shruti", "abc@123");
        user2 = new User("admin", "admin", "admin");
    }

    @Test
    void testLoginUser() throws InvalidCredentialsException {
        when(userRepository.findByUserName(user1.getUserName())).thenReturn(user1);
        assertEquals(user1, userAuthenticationService.loginUser(user1));
    }

    @Test
    void testLoginUser_Exception() {
        when(userRepository.findByUserName(user1.getUserName())).thenReturn(null);
        assertThrows(InvalidCredentialsException.class, () -> userAuthenticationService.loginUser(new User("Shruti", "1234")));
    }

    @Test
    void testLoginUser_InvalidUser() {

        when(userRepository.findByUserName(any())).thenReturn(user1);
        User user = new User();
        user.setUserName("demo");
        user.setPassword("demo");
        assertThrows(InvalidCredentialsException.class, () -> userAuthenticationService.loginUser(user));
    }

}
