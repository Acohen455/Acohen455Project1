package com.revature.DTOs;

//DTO for transferring user and pass
//this way we dont have to transfer a whole user
public class LoginDTO {

    public String username;
    public String password;


    //structs
    public LoginDTO() {
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO(com.revature.models.User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    //setters and getters
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

    //TODO: Custom tostring
    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
