package com.revature.DTOs;

//Using a DTO to strip the password from responses

import com.revature.models.User;

public class UserDTO {

    private int userId;
    private String username;
    private String role;



    //empty struct
    public UserDTO(){

    }

    //struct with user param
    public UserDTO(User user) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    //struct with individual fields
    public UserDTO(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    //getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //TODO: Make a custom ToString
    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
