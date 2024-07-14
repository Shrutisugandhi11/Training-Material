package com.epam.service;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    User user;
    List<User> users;

    @BeforeEach
    void setUp() {
        user = new User("abcd", "xyz", "abc@gmail.com");
        users = Arrays.asList(user);
    }


    @Test
    void testAddUser() {
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.addUser(user));
    }

    @Test
    void testGetUsers() {
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(users.size(), userService.getAllUsers().size());

    }
    @Test
    void testGetUserByUsername() {
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUser(user.getUsername()));
    }
    @Test
    void testUpdateUser() {
        when(userRepository.findByUsername(any())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(any())).thenReturn(user);
        User user1 = userService.updateUser(user.getUsername(),user);
        user1.setEmail("demo@gmail.com");
        assertEquals("demo@gmail.com", user1.getEmail());
    }

    @Test
    void testDeleteUser() throws Exception {
        User user1 = new User("python", "xyz", "abc");
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user1)) ;
        userService.deleteUser(user1.getUsername());
        verify(userRepository).delete(user1);
    }

}
