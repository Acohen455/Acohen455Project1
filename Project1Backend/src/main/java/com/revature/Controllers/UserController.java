package com.revature.Controllers;

import com.revature.DAOs.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {

    private final User user;
    //need the user service
    ReimbursementService reimbursementService;

    @Autowired
    public UserController(ReimbursementService reimbursementService, User user) {
        this.reimbursementService = reimbursementService;
        this.user = user;
    }


    //function for getting user reimbursements
    @GetMapping("/reimbursements")
    public ResponseEntity<List<Reimbursement>> getUserReimbursements(HttpSession session) {
        //one liner for populating user reimbursements
        List<Reimbursement> userReimbList = reimbursementService.findByUserId(
                (Integer) session.getAttribute("userId")).orElse(Collections.emptyList());

        //return the responseentity
        return ResponseEntity.ok(userReimbList);
    }

    //make sure we bind the requestbody to the reimbursement
    //this is otherwise a super simple function
    @PostMapping("/createreimbursement")
    public ResponseEntity<Reimbursement> createReimbursement(HttpSession session,
                                                             @RequestBody Reimbursement reimbursement) {

        //call the service layer to create the reimbursement
        Reimbursement createdReimb = reimbursementService.createReimbursement(reimbursement);


        return ResponseEntity.ok(createdReimb);
    }

    //for grabbing by status -- could use database calls or sort an existing list
    //can also do a hybrid method -- grab all, then sort -- im doing this
    //TODO: MAKE SURE WE PASS THE PARAMETER
    @GetMapping("/pendingreimbursements")
    public ResponseEntity<List<Reimbursement>> getReimbursementsByPendingStatus(HttpSession session,
                                                                                @RequestParam boolean status) {
        //call the service layer to get reimbursements by status
        //to make sure the user can only access their own, we grab their ID off the session token
        List<Reimbursement> pendingList = reimbursementService.findByStatusAndUserId(status,
                                                                (Integer) session.getAttribute("userId"))
                                                                .orElse(Collections.emptyList());

        //return the entity
        return ResponseEntity.ok(pendingList);
    }

    @PostMapping("/updatedescription")
    public ResponseEntity<Reimbursement> updateDescription(HttpSession session, @RequestBody String description) {
        //call the service layer to update the description
        Reimbursement updatedReimb = reimbursementService.updateReimbursementDescription(description,
                (Integer) session.getAttribute("userId")).orElse(null);

        //if it failed, throw exception
        if (updatedReimb == null) {
            throw new IllegalArgumentException("Reimbursement not found");
        }

        //if it succeeded, return the ResponseEntity
        return ResponseEntity.ok(updatedReimb);
    }

}
