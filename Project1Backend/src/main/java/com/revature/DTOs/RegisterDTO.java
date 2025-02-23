package com.revature.DTOs;

public class RegisterDTO {
    public String username;
    public String password;
    public String role;
    public String firstName;
    public String lastName;


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

    public RegisterDTO(String username, String password, String role, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
