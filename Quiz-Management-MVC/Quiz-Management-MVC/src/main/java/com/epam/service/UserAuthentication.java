package com.epam.service;


import com.epam.exception.CredentialsMismatchException;

import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserAuthentication {
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(UserAuthentication.class);
    @Autowired
    UserService userService;

    public boolean validateUser(String username, String password, String roleType) throws CredentialsMismatchException {
        User user;

        List<User>users=userService.getUsersList();
        boolean isValidate = false;

        Optional<User> optionalUser = users.stream().filter(x -> roleType.equals(x.getRoleType()))
                .filter(x -> username.equals(x.getUserName())).findAny();

        if (optionalUser.isPresent()) {
            user = optionalUser.get();

            if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
                if ("admin".equals(user.getRoleType()) || "user".equals(user.getRoleType()))
                    isValidate = true;

            } else {
                throw new CredentialsMismatchException("Invalid credentials ,Please Enter valid credentials!!!");
            }
        } else {
            throw new CredentialsMismatchException("Error while validating user please retry....");
        }


        return isValidate;
    }

    public boolean validateCredentials(String userName, String passwordString) {
        String usernameRegex = "^[A-Za-z]\\w{5,29}$";
        String passwordRegex = "^(?=.*[\\d])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{6,20}$";
        Pattern userNamePattern = Pattern.compile(usernameRegex);
        Matcher userNameMatcher = userNamePattern.matcher(userName);
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(passwordString);
        return userNameMatcher.matches() && passwordMatcher.matches();
    }

    public boolean isUserExsist(String userName) {
        boolean flag = false;

        for (User user : userService.getUsersList()) {
            if (userName.equals(user.getUserName())) {
                flag = true;
                break;
            }
        }
        return flag;

    }


}