package com.epam.service;


import com.epam.dao.UserDaoImpl;
import com.epam.exception.CredentialsMismatchException;
import com.epam.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationTest {

    @InjectMocks
    UserAuthentication userAuthentication;
    @Mock
    UserService userService;
     @Mock
     UserDaoImpl userDao;
    User user;
    User user1;

    List<User> users;

    @BeforeEach
    void setup() {
        user = new User();
        user.setUserName("xxx");
        user.setPassword("111");
        users = new ArrayList<>();
        users.add(user);
        user1 = new User("Shruti@12", "Sugandhi@02#");
        users.add(user1);
    }


    @Test
    void isUserExist() {
        when(userService.getUsersList()).thenReturn(users);
        userAuthentication.isUserExsist("xxx");
        assertFalse(userAuthentication.isUserExsist("www"));
        assertTrue(userAuthentication.isUserExsist("xxx"));
    }

   @Test
 void validateUser() throws CredentialsMismatchException {

    when(userService.getUsersList()).thenReturn(users);
    assertTrue(userAuthentication.validateUser("xxx", "111", "user"));
    assertThrows(CredentialsMismatchException.class,()->userAuthentication.validateUser("www","123","user"));
   }
   @Test
    void validateCredentials(){
       assertTrue(userAuthentication.validateCredentials("Nandini7","Sugandhi@9"));
       assertFalse(userAuthentication.validateCredentials("abc","123"));
   }

}
