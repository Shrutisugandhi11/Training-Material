package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
 class UserControllerTest {
    @InjectMocks
    UserController userController;
    @MockBean
    UserService userService;
    User user;
    List<User> users;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        user = new User("shruti", "sugandhi");
        users = Arrays.asList(user);
    }

    @Test
    void testUserList() throws Exception {
        when(userService.getUsersList()).thenReturn(users);
        mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(view().name("userList"));

    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.register(user.getUserName(), user.getPassword())).thenReturn("User registered Successfully!! ");
        mockMvc.perform(get("/users/new")).andExpect(status().isOk()).andExpect(view().name("createUser"));
    }
    @Test
    void testSaveUser() throws Exception {
        when(userService.register(user.getUserName(), user.getPassword())).thenReturn("User registered Successfully!! ");
        mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(view().name("userList"));
    }
    @Test
    void  delete() throws Exception {
        when(userService.removeUser(user.getUserName())).thenReturn(user);
        mockMvc.perform(get("/delete")).andExpect(status().isOk()).andExpect(view().name("userList"));
    }

}
