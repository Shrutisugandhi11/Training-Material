package com.epam.service;


import com.epam.exception.InvalidCredentialsException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class UserAuthenticationService {
    @Autowired
    UserRepository userRepository;

    public User loginUser(User user) throws InvalidCredentialsException {
        String username = user.getUserName();
        String password = user.getPassword();


        User existingUser = userRepository.findByUserName(username);


        if (Objects.isNull(existingUser)) {
            throw new InvalidCredentialsException("User not found");
        }
        if (!username.equals(existingUser.getUserName()) || !password.equals(existingUser.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return existingUser;
    }


}