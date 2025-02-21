package com.revature.Controllers;

import com.revature.DTOs.LoginDTO;
import com.revature.DTOs.RegisterDTO;
import com.revature.DTOs.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
        //turn the user into a DTO
        RegisterDTO newUser = new RegisterDTO(
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );



        // pass password encoding responsibilities to the service layer
        UserDTO registeredUser = authService.registerUser(newUser);

        //use a response entity to not only return the registered user but also a status code
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    //user login method
    //same as with registration, anything that involves the DAO etc. goes to service layer
    @PostMapping("/login")
    public ResponseEntity<UserDTO> userLogin(@RequestBody LoginDTO incomingUser, HttpSession session) {

        UserDTO loggedInUser = authService.login(incomingUser);



        //return the user if the info is valid
        //I prefer to explicitly type the return
        //implicit types make me feel yicky
        return new ResponseEntity<UserDTO>(outgoingUser, HttpStatus.OK);
    }
}