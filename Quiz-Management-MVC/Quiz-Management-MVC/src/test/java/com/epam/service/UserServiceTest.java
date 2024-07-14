package com.epam.service;


import com.epam.dao.UserDaoImpl;
import com.epam.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserDaoImpl userDao;
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
    void create(){
        when(userDao.create(user)).thenReturn(user);
        assertEquals("User registered Successfully!! ", userService.register(user.getUserName(),user.getPassword()));
        assertEquals( "userName OR password not found!!",userService.register("",""));

    }

    @Test
    void delete() {
        when(userDao.delete("xxx")).thenReturn(user);
        assertEquals(user, userService.removeUser("xxx"));

    }
    @Test
    void getUsers(){
      when(userDao.findAll()).thenReturn(users) ;
      assertEquals(users.size(),userService.getUsersList().size());
    }
    @Test
    void readAllUser(){
        userService.getAllUsers();
        verify(userDao).findAll();
    }


    }











