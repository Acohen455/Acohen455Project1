package com.revature.services;

import com.revature.models.User;
import com.revature.DTOs.UserDTO;
import com.revature.DAOs.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.revature.DTOs.LoginDTO;

import java.util.Optional;

@Service
public class AuthService {

    private final UserDAO userDAO;
    private final User user;

    //TODO: add password encoders possibly
    //private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserDAO userDAO, User user) {
        this.userDAO = userDAO; //let spring create the DAO
        this.user = user;
    }



    public Optional<User> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public UserDTO registerUser(User user) {

        //save the user -- pops back the saved object
        User returnedUser = userDAO.save(user);

        //turn this into the DTO to get rid of password
        //order of args is id, user, role
        UserDTO outgoingUser = new UserDTO(
                returnedUser.getUserId(),
                returnedUser.getUsername(),
                returnedUser.getRole()
        );


        return outgoingUser;
    }

    public UserDTO login(LoginDTO userLogin){

        //do some input validation
        //username validation here
        if (userLogin.getUsername() == null || userLogin.getUsername().isBlank()){
            throw new IllegalArgumentException("Dude, come on! You can't login with no username!");
        }

        //input validation for pass now
        if (userLogin.getPassword() == null || userLogin.getPassword().isBlank()){
            throw new IllegalArgumentException("Dude, come on! You can't login with no password!");
        }

        //just for fun, lets make sure the characters are roman alphabet





    }

    




}