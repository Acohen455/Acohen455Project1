package com.revature.Controllers;

import com.revature.models.User;
import com.revature.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//CORS stuff is in AppConfig.java
//This helps w/ maintainability
@RestController
@RequestMapping("/auth")
public class AuthController {

    //we'll inject this with spring
    private final AuthService authService;

    //injecting the authservice
    @Autowired
    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    //function for registering the user
    //make sure we map this to a post request
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // pass password encoding responsibilities to the service layer
        User registeredUser = authService.registerUser(user);

        //use a response entity to not only return the registered user but also a status code
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    //user login method
    //same as with registration, anything that involves the DAO etc. goes to service layer
    @PostMapping("/login")
    public ResponseEntity<User> userLogin(String username, String password) {
        //check if the user exists
        //we're able to use orElse because findBy uses an optional
        User user = authService.findByUsername(username).orElse(null);
        //if the user doesnt exist or info is incorrect, return a null user
        if (user == null || authService.checkPassword(user, password) == false) {
            return new ResponseEntity<User>(null, HttpStatus.UNAUTHORIZED);
        }
        //return the user if the info is valid
        //I prefer to explicitly type the return
        //implicit types make me feel yicky
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}