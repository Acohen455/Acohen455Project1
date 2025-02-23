package com.revature.Controllers;

import com.revature.DTOs.LoginDTO;
import com.revature.DTOs.RegisterDTO;
import com.revature.DTOs.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
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
    //changing the parameters to params instead of a requestbody
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user, HttpServletRequest request) {

        if (user.getRole() == null) {
            user.setRole("USER");
        }

        //turn the user into a DTO
        RegisterDTO newUser = new RegisterDTO(
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName()
        );



        // pass password encoding responsibilities to the service layer
        UserDTO registeredUser = authService.registerUser(newUser);

        HttpSession session = request.getSession(true);
        if (session.getAttribute("role") == null) {
            session.setAttribute("userId", registeredUser.getUserId());
            session.setAttribute("username", registeredUser.getUsername());
            session.setAttribute("role", registeredUser.getRole());
        }

        //use a response entity to not only return the registered user but also a status code
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    //user login method
    //same as with registration, anything that involves the DAO etc. goes to service layer
    @PostMapping("/login")
    public ResponseEntity<UserDTO> userLogin(@RequestBody LoginDTO incomingUser, HttpSession session) {

        UserDTO loggedInUser = authService.login(incomingUser);

        session.setAttribute("userId", loggedInUser.getUserId());
        session.setAttribute("username", loggedInUser.getUsername());
        session.setAttribute("role", loggedInUser.getRole().toUpperCase());


        //debug
        System.out.println("✅ Session Created: " + session.getId());
        System.out.println("🔐 Session Attributes: ");
        System.out.println("   - userId: " + session.getAttribute("userId"));
        System.out.println("   - username: " + session.getAttribute("username"));
        System.out.println("   - role: " + session.getAttribute("role"));

        //return the user if the info is valid
        //I prefer to explicitly type the return
        //implicit types make me feel yicky
        return new ResponseEntity<UserDTO>(loggedInUser, HttpStatus.OK);
    }
}