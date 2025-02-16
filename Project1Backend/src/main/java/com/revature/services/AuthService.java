package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO; //let spring create the DAO
        this.passwordEncoder = passwordEncoder; //let spring create the password encoder
    }


    //function for registering the user
    public User registerUser(User user) {
        //use the spring password encoder to hash the password
        //we can match a provided password using the PasswordEncoder.matches() method
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //save the user to the database then return the saved object
        return userDAO.save(user);
    }

    //user login method
    public boolean userLogin(String username, String password) {
        //check if the user exists
        //we're able to use orElse because findBy uses an optional
        User user = userDAO.findByUsername(username).orElse(null);
        //if the user doesnt exist, return false
        if (user == null) {
            return false;
        }
        //return true or false depending on whether password matches
        return passwordEncoder.matches(password, user.getPassword());
    }

}