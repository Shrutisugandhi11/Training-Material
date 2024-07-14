package com.epam.controller;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User user;

    private List<User> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        user = new User("abcd", "xyz", "abc@gmail.com");
        users = Arrays.asList(user);
    }

    @Test
    void testCreateUser() throws Exception {

        when(userService.addUser(user)).thenReturn(user);
        mockMvc.perform(post("/users").contentType("application/json").content(objectMapper.writeValueAsString(user))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testGetUser() throws Exception {
        when(userService.getUser(any())).thenReturn(user);
        mockMvc.perform(get("/users/abcd")).andExpect(status().isOk());
    }

    @Test
    void testGetUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(users);
        mockMvc.perform(get("/users")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(user.getUsername(), user)).thenReturn(user);
        mockMvc.perform(put("/users/abcd").contentType("application/json").content(objectMapper.writeValueAsString(user))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/abcd")).andExpect(status().isOk());
    }


}
