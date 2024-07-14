package com.epam.service;


import com.epam.exception.InvalidCredentialsException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    User user;

    public User registerUser(User user) throws InvalidCredentialsException {
        if ("".equals(user.getUserName()) || "".equals(user.getPassword())) {
            throw new InvalidCredentialsException("Username or Password not found!!");
        } else {
            userRepository.save(user);
        }
        return user;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    public void removeUser(int userId) {
        userRepository.deleteById(userId);
    }

}



