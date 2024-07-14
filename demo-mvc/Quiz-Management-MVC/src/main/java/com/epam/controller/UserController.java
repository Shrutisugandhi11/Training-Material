//package com.epam.controller;
//
//
//import com.epam.model.User;
//import com.epam.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class UserController {
// @Autowired
//  private UserService userService;
//
//    @PostMapping("/add")
//    public ModelAndView addUser(User user){
//        ModelAndView modelAndView=new ModelAndView();
//       String msg =userService.register(user.getUserName(),user.getPassword());
//        modelAndView.addObject( "msg",msg);
//        modelAndView.setViewName("index.html");
//
//       return modelAndView ;
//    }
//
//
//}
