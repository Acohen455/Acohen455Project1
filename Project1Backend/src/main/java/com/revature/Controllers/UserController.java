package com.revature.Controllers;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/*
* Users need to be able to:
* See their own reimbursements
* Sort their own reimbursements by status
*
 */


@RestController
public class UserController {

    private final User user;
    private final UserService userService;
    //need the user service
    ReimbursementService reimbursementService;

    @Autowired
    public UserController(ReimbursementService reimbursementService, User user, UserService userService) {
        this.reimbursementService = reimbursementService;
        this.user = user;
        this.userService = userService;
    }


    //function for getting user reimbursements
    @GetMapping("/reimbursements")
    public ResponseEntity<List<Reimbursement>> getUserReimbursements(HttpSession session, @RequestParam(required=false) Integer userId) {
        //debugging


        if (userId == null){
            userId = (Integer) session.getAttribute("userId");
        }



        //one liner for populating user reimbursements
        //repurposed to take a param so we can use this for filter
        List<Reimbursement> userReimbList = reimbursementService.findByUserId(userId).orElse(Collections.emptyList());

        //return the responseentity
        return ResponseEntity.ok(userReimbList);
    }

    //make sure we bind the requestbody to the reimbursement
    //this is otherwise a super simple function
    @PostMapping("/createreimbursement")
    public ResponseEntity<Reimbursement> createReimbursement(HttpSession session,
                                                             @RequestBody Reimbursement reimbursement,
                                                             @RequestParam(required = false) Integer userId) {

        if ((((String)session.getAttribute("role")).equalsIgnoreCase("user")) ||
            userId == null) {
            reimbursement.setUser(userService.getUserById((Integer)session.getAttribute("userId")).getBody().orElse(null));
        } else {
            reimbursement.setUser(userService.getUserById(userId).getBody().orElse(null));
        }



        if (reimbursement.getUser() == null) {
            throw new IllegalArgumentException("User not found");
        }


        //call the service layer to create the reimbursement
        Reimbursement createdReimb = reimbursementService.createReimbursement(reimbursement);


        return ResponseEntity.ok(createdReimb);
    }

    //for grabbing by status -- could use database calls or sort an existing list
    //can also do a hybrid method -- grab all, then sort -- im doing this
    //TODO: MAKE SURE WE PASS THE PARAMETER
    @GetMapping("/pendingreimbursements")
    public ResponseEntity<List<Reimbursement>> getReimbursementsByStatus(HttpSession session,
                                                                         @RequestParam String status) {
        //call the service layer to get reimbursements by status
        //to make sure the user can only access their own, we grab their ID off the session token
        List<Reimbursement> pendingList = reimbursementService.findByStatusAndUserId(status,
                                                                (Integer) session.getAttribute("userId"))
                                                                .orElse(Collections.emptyList());

        //return the entity
        return ResponseEntity.ok(pendingList);
    }

    @PostMapping("/updatedescription/{reimbursementId}")
    public ResponseEntity<Reimbursement> updateDescription(@PathVariable int reimbursementId, @RequestBody String description) {

        //if we cant find the reimbursement, throw an exception
        if (reimbursementService.findByReimbursementId(reimbursementId).isEmpty()) {
            throw new IllegalArgumentException("Reimbursement not found");
        }


        //call the service layer to update the description
        //dont return anything back
        reimbursementService.updateReimbursementDescription(description, reimbursementId);


        //if it succeeded, return the ResponseEntity
        return ResponseEntity.ok().build();
    }

}
