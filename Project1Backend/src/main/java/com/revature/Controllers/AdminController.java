package com.revature.Controllers;

import com.revature.DTOs.UserDTO;
import com.revature.aspects.AdminOnly;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.models.Reimbursement;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ReimbursementService reimbursementService;

    private final String pendingStatus = "PENDING";
    private final String approvedStatus = "APPROVED";
    private final String deniedStatus = "DENIED";

    @Autowired
    public AdminController(UserService userService, ReimbursementService reimbursementService) {
        this.userService = userService;
        this.reimbursementService = reimbursementService;
    }



    //return all users to the client
    @GetMapping("/users")
    @AdminOnly
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //this method finds either pending or closed reimbursements depending on whats passed
    //true = pending, false = closed
    //TODO: MAKE SURE WE SEND THE BOOLEAN AS A PARAMETER IN AXIOS
    //https://chat.deepseek.com/a/chat/s/caaa4e44-e60e-43fc-b2a7-f491ef7363d5
    @GetMapping("/pendingreimbursements")
    @AdminOnly
    public ResponseEntity<List<Reimbursement>> getReimbursementsByPending(){
        //create list and populate
        //we can do this with control flow logic, but easier this way
        List<Reimbursement> pendingList = reimbursementService.findByStatus(pendingStatus).orElse(Collections.emptyList());
        //return the list with an ok status code
        return ResponseEntity.ok(pendingList);
    }

    //function for getting approved or declined reimbursements depending on passed bool
    //true = approved, false = declined
    @GetMapping("/approvedreimbursements")
    @AdminOnly
    public ResponseEntity<List<Reimbursement>> getApprovedReimbursements(){
        //this code is the same as pending reimbursement code
        //list for returning
        List<Reimbursement> pendingList = reimbursementService.findByStatus(approvedStatus).orElse(Collections.emptyList());
        //return the list with an ok status code
        return ResponseEntity.ok(pendingList);
    }

    @GetMapping("/deniedreimbursements")
    @AdminOnly
    public ResponseEntity<List<Reimbursement>> getDeniedReimbursements(){
        //this code is the same as pending reimbursement code
        //list for returning
        List<Reimbursement> pendingList = reimbursementService.findByStatus(deniedStatus).orElse(Collections.emptyList());
        //return the list with an ok status code
        return ResponseEntity.ok(pendingList);
    }

    @GetMapping("/userreimbursements")
    @AdminOnly
    public ResponseEntity<List<Reimbursement>> getUserReimbursements(@RequestParam int userId){
        //this code is the same as pending reimbursement code
        //list for returning
        List<Reimbursement> userReimbList = reimbursementService.findByUserId(userId).orElse(Collections.emptyList());

        //return the list with an ok status code
        return ResponseEntity.ok(userReimbList);
    }

    @GetMapping("/reimbursements")
    @AdminOnly
    public ResponseEntity<List<Reimbursement>> getAllReimbursements(){
        return ResponseEntity.ok(reimbursementService.getAllReimbursements());
    }

    //best practice is to use a path variable here instead of a passed parameter
    @DeleteMapping("/deleteuser")
    @AdminOnly
    public ResponseEntity deleteUser(@RequestParam int userId){
        userService.deleteUserById(userId);
        userService.resetUserIdSequence();
        //this is best practice for deletion
        return ResponseEntity.noContent().build();
    }

    //best practice is to pass the role as a request body
    //best practice is to pass the user id as a path variable
    @PatchMapping("/updateuserrole/{userId}")
    @AdminOnly
    public ResponseEntity updateUserRole(@PathVariable int userId, @RequestBody String role){
        return ResponseEntity.ok(userService.updateUserRoleById(userId, role));
    }

    //function for updating reimbursement status
    @PutMapping("/updatereimbursement/{reimbursementId}")
    @AdminOnly
    public ResponseEntity updateReimbursement(@PathVariable int reimbursementId,
                                              @RequestParam String status){

        if (reimbursementService.findByReimbursementId(reimbursementId).isEmpty()) {
            throw new IllegalArgumentException("Reimbursement not found");
        }

        reimbursementService.updateReimbursementStatus(reimbursementId, status);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/registeruser")
    @AdminOnly
    public ResponseEntity registerUser(@RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("role") String role) {
        return ResponseEntity.ok(userService.registerUser(firstName, lastName, username, password, role));
    }




}
