package com.epam.service;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


  private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));


    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String username, User user) {
        User existingUser = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found:"));
        existingUser.setUsername(user.getUsername());
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);
        return existingUser;
    }

    public void deleteUser(String username) {
        User existingUser = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!!"));
        userRepository.delete(existingUser);

    }


}
