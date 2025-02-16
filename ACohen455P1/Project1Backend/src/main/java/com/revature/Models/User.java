package com.revature.models;

import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;

//we make this the entity, as this will represent our db data model
//this ONLY represents the model
//the DAO will handle any CRUD operations and logic

@Component
@Entity
@Table(name = "users")
public class User implements UserDetails{

    @Id //indicates to sprint boot that this is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this makes the primary key increment
    private int userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "role")
    private String role;

    //column for checking if a user can login
    //this is hardcoded to always be true
    //can be changed to enable/disable users
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;




    //boilerlate code
    //empty constructor

    public User() {
    }

    //overloaded constructor

    public User(int userId, String firstName, String lastName, String username, String password, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    //setters and getters

    //setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //getters
    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }


    //ToString
    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}