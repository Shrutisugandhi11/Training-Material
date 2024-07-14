package com.epam.controller;


import com.epam.dto.UserDto;
import com.epam.exception.InvalidCredentialsException;
import com.epam.model.User;
import com.epam.service.UserService;
import com.epam.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class UserController {
    @Autowired
    DtoConverter dtoConverter;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "userList";
    }

    @GetMapping("/user/new")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/user/save")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto) throws InvalidCredentialsException {
        User user = dtoConverter.convertToUser(userDto);
        userService.registerUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user/panel")
    public String userPanel() {
        return "userMenu";
    }


}