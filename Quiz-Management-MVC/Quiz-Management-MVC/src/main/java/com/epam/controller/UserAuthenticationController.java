package com.epam.controller;

import com.epam.model.Admin;
import com.epam.model.User;
import com.epam.service.UserAuthentication;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAuthenticationController {

    @Autowired
    UserAuthentication userAuthentication;
    @Autowired
    UserService userService;

    @GetMapping("/login/user")
    public String loginUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "loginUser";
    }

    @GetMapping("/login/admin")
    public String loginAdmin(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "loginAdmin";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/admin/panel")
    public ModelAndView validateUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (userAuthentication.isUserExsist(user.getUserName())) {
            modelAndView.addObject("validate", true);
            modelAndView.setViewName("adminPanel");
        }
        return modelAndView;
    }


    @PostMapping("/user/panel")
    public String loginPanelAdmin() {
        return "redirect:/login/user";
    }


}
