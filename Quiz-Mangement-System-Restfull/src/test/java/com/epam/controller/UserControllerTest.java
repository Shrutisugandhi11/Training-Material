package com.epam.controller;


import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.service.UserService;
import com.epam.util.DtoConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
 class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserRepository userRepository;
    @MockBean
    UserService userService;
    @MockBean
    DtoConverter dtoConverter;
    User user;
    List<User> users=new ArrayList<>();
    @BeforeEach
    void setUp(){
        user=new User("Shruti","Sugandhi");
        users.add(user);
    }

    @Test
    void  testGetUsers() throws Exception {
        when(userService.getAllUser()).thenReturn(users);
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }
@Test
    void  testCreateUser() throws Exception {
        when(userService.registerUser(user)).thenReturn(user);
        mockMvc.perform(post("/users").contentType("application/json")
                .content(objectMapper.writeValueAsString(user))).andExpect(status().isCreated());
}
@Test
void testLoginUser() throws Exception {
        when(userService.loginUser(user)).thenReturn(user);
        mockMvc.perform(post("/users/login")
                .contentType("application/json").content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
}
@Test
    void  testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1")).andExpect(status().isOk());
}

}
