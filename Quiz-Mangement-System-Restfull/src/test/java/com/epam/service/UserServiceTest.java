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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    User user;
    List<User> users;

    @BeforeEach
    void setup() {
        user = new User();
        user.setUserName("xxx");
        user.setPassword("111");
        users = new ArrayList<>();

        users.add(user);
    }

    @Test
    void testCreateUser() throws InvalidCredentialsException {
        User user1 = new User("", "");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.registerUser(user));
        assertThrows(InvalidCredentialsException.class, () -> userService.registerUser(user1));

    }

    @Test
    void testGetUsers() {

        when(userRepository.findAll()).thenReturn(users);
        assertEquals(users.size(), userService.getAllUser().size());

    }
    @Test
    void testRemoveUser() {
        userService.removeUser(user.getUserId());
        verify(userRepository).deleteById(user.getUserId());

    }
    @Test
    void testLoginUser() throws InvalidCredentialsException {
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
        assertEquals(user, userService.loginUser(user));
    }

}
