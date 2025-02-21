package com.revature.Controllers;

import com.revature.DTOs.UserDTO;
import com.revature.aspects.AdminOnly;
import com.revature.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.Models.Reimbursement;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }



    //return all users to the client
    @GetMapping("/users")
    @AdminOnly
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    public ResponseEntity<List<Reimbursement>> getReimbursementsByPending{


    }




}
