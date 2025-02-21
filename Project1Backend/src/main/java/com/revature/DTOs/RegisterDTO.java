package com.revature.DTOs;

public class RegisterDTO {
    public String username;
    public String password;
    public String role;


    //constructors
    public RegisterDTO() {
    }

    public RegisterDTO(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = null;
    }

    public RegisterDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }



    //setters & getters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //toString
    //TODO: customize this

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
