package com.epam.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {
    int userId;
    @Size(min = 8, max = 18, message = "username should be in range of 8-18 letters!!")
    @NotBlank(message = "UserName cannot be Blank")
    String userName;
    @Size(min = 8, max = 18, message = "password should be in range of 8-18 letters!!")
    @NotBlank(message = "password cannot be Blank")
    String password;
    String roleType = "user";

    public UserDto() {
    }

    public UserDto(int userId, String userName, String password, String roleType) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.roleType = roleType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
