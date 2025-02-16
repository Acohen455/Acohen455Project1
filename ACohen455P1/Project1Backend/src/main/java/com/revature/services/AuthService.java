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


    //encoding and passing stuff to the DAO happens here
    public User registerUser(User user) {
        // pass password encoding responsibilities to the service layer
        // use the spring password encoder to hash the password
        //we can match a provided password using the PasswordEncoder.matches() method
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    //simple one liner function for matching password hashes
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }


    //method for creating authentication token with JWT
    public String generateToken()

    //According to deepthink, claims are what we should use here -- claims are key-value pairs representing info about the user
    //we'll store user roles this way


}