package com.epam.controller;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.service.UserAuthenticationService;
import com.epam.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserAuthenticationControllerTest {
    @InjectMocks
    UserAuthenticationController userAuthenticationController;
    @MockBean
    UserService userService;
    @MockBean
    UserAuthenticationService userAuthenticationService;
    User user;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        user = new User("shruti", "sugandhi");

    }

    @Test
    void testLoginUser() throws Exception {
        mockMvc.perform(get("/login/user")).andExpect(status().isOk()).andExpect(view().name("loginUser"));
    }

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void testloginPanelAdmin() throws Exception {
        mockMvc.perform(post("/user/panel")).andExpect(view().name("redirect:/login/user"));

    }

    @Test
    void validateUser_Admin() throws Exception {
        User user1 = new User("admin", "admin", "admin");
        when(userAuthenticationService.loginUser(any())).thenReturn(user1);
        mockMvc.perform(post("/login/panel").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userName", "admin").param("password", "admin")).andExpect(view().name("adminPanel"));
    }


    @Test
    void validateUser_User() throws Exception {
        User user1 = new User("Shruti", "abc@123", "user");
        when(userAuthenticationService.loginUser(any())).thenReturn(user1);
        mockMvc.perform(post("/login/panel").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userName", "Shruti").param("password", "abc@123")).andExpect(status().is3xxRedirection());    }

}
