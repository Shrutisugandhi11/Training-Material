package com.epam.controller;

import com.epam.dto.UserDto;
import com.epam.exception.InvalidCredentialsException;
import com.epam.model.User;
import com.epam.service.UserAuthenticationService;
import com.epam.service.UserService;
import com.epam.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserAuthenticationController {
    @Autowired
    DtoConverter dtoConverter;
    @Autowired
    UserAuthenticationService userAuthenticationService;
    @Autowired
    UserService userService;

    @GetMapping("/login/user")
    public String loginUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "loginUser";
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login/panel")
    public String validateUser(@Valid @ModelAttribute("user") UserDto userDto) throws InvalidCredentialsException {
       User user=dtoConverter.convertToUser(userDto);
        String redirect;
        User user1 = userAuthenticationService.loginUser(user);
        if (user1.getRoleType().equals("admin")) {
            redirect = "adminPanel";
        } else {
            redirect = "redirect:/quiz/get/quiz";
        }
        return redirect;
    }


    @PostMapping("/user/panel")
    public String loginPanelAdmin() {
        return "redirect:/login/user";
    }

}
