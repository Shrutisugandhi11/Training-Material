package com.epam.controller;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    @MockBean
    UserRepository userRepository;
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
        when(userService.getAllUser()).thenReturn(users);
        mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(view().name("userList"));

    }
    @Test
    void testCreateUser() throws Exception {
        when(userService.registerUser(user)).thenReturn(user);
        mockMvc.perform(get("/user/new")).andExpect(status().isOk()).andExpect(view().name("createUser"));
    }
    @Test
    void testSaveUser() throws Exception {
        when(userService.registerUser(user)).thenReturn(user);
        mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userName", "admin").param("password", "admin"))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/users"));
    }

    @Test
    void  delete() throws Exception {
        userService.removeUser(user.getUserId());
        userRepository.deleteById(user.getUserId());
        mockMvc.perform(get("/delete/1")).andExpect(status().is3xxRedirection());
    }

    @Test
    void  testUserPanel() throws Exception {
        mockMvc.perform(get("/user/panel")).andExpect(status().isOk()).andExpect(view().name("userMenu"));

    }

    }
