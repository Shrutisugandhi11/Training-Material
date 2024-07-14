package com.epam.service;


import com.epam.exception.InvalidCredentialsException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    User user;

    public User registerUser(User user) throws InvalidCredentialsException {

        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new InvalidCredentialsException("User Name already taken!!");
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

    public User loginUser(User user) throws InvalidCredentialsException {
        String username = user.getUserName();
        String password = user.getPassword();

        User existingUser = userRepository.findByUserName(username).orElseThrow();

        if (Objects.isNull(existingUser)) {
            throw new InvalidCredentialsException("User not found");
        }
        if (!password.equals(existingUser.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        return existingUser;
    }

}



