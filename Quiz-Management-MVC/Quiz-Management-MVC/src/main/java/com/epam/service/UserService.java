package com.epam.service;


import com.epam.dao.UserDao;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(UserService.class);
    @Autowired
    UserDao<User, String> userDao;

    User user;

    public String register(String userName, String passwordString) throws NullPointerException {
        String returnString;
        if ("".equals(userName) || "".equals(passwordString)) {
            returnString = "userName OR password not found!!";
        } else {
            user = new User(userName, passwordString);
            userDao.create(user);
            returnString = "User registered Successfully!! ";
        }
        return returnString;
    }

    public List<User> getUsersList() throws NullPointerException {
        return userDao.findAll();
    }

    public String getAllUsers() {
        List<User> users = userDao.findAll();
        users.stream().forEach(Logger::info);
        return "User List Displayed";
    }

    public User removeUser(String username) {
        return userDao.delete(username);
    }

}



