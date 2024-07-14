package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
        User updateUser = userService.updateUser(username, user);
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);

    }

}
