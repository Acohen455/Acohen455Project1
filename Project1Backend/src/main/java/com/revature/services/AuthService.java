package com.revature.services;

import com.revature.DTOs.RegisterDTO;
import com.revature.models.User;
import com.revature.DTOs.UserDTO;
import com.revature.DAOs.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.revature.DTOs.LoginDTO;
import com.revature.Util.InputChecker;

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

    public UserDTO registerUser(RegisterDTO newUser) {
        InputChecker newInpChecker = new InputChecker();


        System.out.println(newUser.toString());


        //do some input validation
        //same validation as for login
        //username validation here
        if (newUser.getUsername() == null || newUser.getUsername().isBlank()){
            throw new IllegalArgumentException("Sorry, you don't seem to have a username!");
        }

        //input validation for pass now
        if (newUser.getPassword() == null || newUser.getPassword().isBlank()){
            throw new IllegalArgumentException("Sorry, you don't seem to have a password!");
        }

        //just for fun, lets make sure the characters are roman alphabet
        if(!(newInpChecker.RomanAlphabetChecker(newUser.getUsername())) ||
                !(newInpChecker.RomanAlphabetChecker(newUser.getPassword()))){
            throw new IllegalArgumentException("That doesn't seem to be the roman alphabet!");
        }

        //if user doesnt have a role, make them a user role
        if (newUser.getRole() == null) {
            newUser.setRole("USER");
        }


        User userToSave = new User(
                newUser.getUsername(),
                newUser.getPassword(),
                newUser.getRole(),
                newUser.getFirstName(),
                newUser.getLastName()
        );

        //save the user -- pops back the saved object
        User returnedUser = userDAO.save(userToSave);



        //turn this into outgoing DTO to get rid of password
        //order of args is id, user, role
        UserDTO outgoingUser = new UserDTO(
                returnedUser.getUserId(),
                returnedUser.getUsername(),
                returnedUser.getRole(),
                returnedUser.getFirstName(),
                returnedUser.getLastName()
        );

        //return the DTO
        return outgoingUser;
    }

    public UserDTO login(LoginDTO userLogin){
        //input checker
        InputChecker newInpChecker = new InputChecker();


        //do some input validation
        //username validation here
        if (userLogin.getUsername() == null || userLogin.getUsername().isBlank()){
            throw new IllegalArgumentException("You can't login with no username!");
        }

        //input validation for pass now
        if (userLogin.getPassword() == null || userLogin.getPassword().isBlank()){
            throw new IllegalArgumentException("You can't login with no password!");
        }

        //just for fun, lets make sure the characters are roman alphabet
        if(!(newInpChecker.RomanAlphabetChecker(userLogin.getUsername())) ||
                !(newInpChecker.RomanAlphabetChecker(userLogin.getPassword()))){
            throw new IllegalArgumentException("That doesn't seem to be English");
        }

        //now get the user using the info
        //if cant find, return null
        User returnedUser = userDAO.findByUsernameAndPassword(userLogin.getUsername(),
                                                                    userLogin.getPassword()).orElse(null);

        //make sure user info is valid
        if (returnedUser == null){
            throw new IllegalArgumentException("Your Username or Password is invalid!");
        }

        //if we get here, login was successful
        return new UserDTO(returnedUser);
    }


    public boolean checkPassword(LoginDTO incomingUser){
        return userDAO.findByUsernameAndPassword(incomingUser.getUsername(),
                incomingUser.getPassword()).isPresent();
    }






}