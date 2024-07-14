package com.epam.controller;

import com.epam.model.Admin;
import com.epam.model.User;
import com.epam.service.UserAuthentication;
import com.epam.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAuthenticationControllerTest {
    @InjectMocks
    UserAuthenticationController userAuthenticationController;
    @MockBean
    UserService userService;
    @MockBean
    UserAuthentication userAuthentication;
    @Autowired
    private MockMvc mockMvc;
    User user;


    @BeforeEach
    void setUp() {
        user = new User("shruti", "sugandhi");
    }

    @Test
    void testLoginUser() throws Exception {
        mockMvc.perform(get("/login/user")).andExpect(status().isOk()).andExpect(view().name("loginUser"));
    }
    @Test
    void testLoginAdmin() throws Exception {
        mockMvc.perform(get("/login/admin")).andExpect(status().isOk()).andExpect(view().name("loginAdmin"));
    }
    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

   @Test
    void  testAdminPanel() throws Exception {
        mockMvc.perform(post("/user")).andExpect(view().name("redirect:/login/user"));

   }
}
