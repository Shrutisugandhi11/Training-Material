package com.epam.controller;


import com.epam.dto.UserDto;
import com.epam.model.User;
import com.epam.service.UserService;
import com.epam.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    DtoConverter dtoConverter;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUser();
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
        User user = dtoConverter.convertToUser(userDto);
        userService.registerUser(user);
        return new ResponseEntity<>("User Created Successfully!!", HttpStatus.CREATED);

    }

    @PostMapping("/users/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserDto userDto) {
        User user = dtoConverter.convertToUser(userDto);
        userService.loginUser(user);
        return new ResponseEntity<>("User Logged in Successfully!!", HttpStatus.OK);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.removeUser(id);
        return new ResponseEntity<>("User Deleted Successfully!!", HttpStatus.OK);
    }


}