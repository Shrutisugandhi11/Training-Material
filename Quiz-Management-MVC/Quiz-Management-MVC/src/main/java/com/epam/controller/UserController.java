package com.epam.controller;


import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "userList";
    }

    @GetMapping("/user/new")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.register(user.getUserName(), user.getPassword());
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam String username) {
        userService.removeUser(username);
        return "redirect:/users";
    }

    @GetMapping("/user/panel")
    public String userPanel(){
        return "userMenu";
    }


}