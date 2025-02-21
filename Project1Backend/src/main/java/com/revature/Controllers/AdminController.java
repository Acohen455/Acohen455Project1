package com.revature.Controllers;

import com.revature.DTOs.UserDTO;
import com.revature.aspects.AdminOnly;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.revature.models.Reimbursement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ReimbursementService reimbursementService;

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
    public ResponseEntity<List<Reimbursement>> getReimbursementsByPendingStatus(@RequestParam boolean status){

        //will come back with an empty optional if no reimbursements are pending
        Optional<List<Reimbursement>> reimbList = reimbursementService.findByStatus(status);

        //list for returning
        List pendingList = new ArrayList<Reimbursement>();

        //check if there are any reimbursements meeting the req
        if (reimbList.isEmpty()){
            return ResponseEntity.ok(pendingList);
        } else {
            //could use this or a for each loop, shouldnt matter
                pendingList.addAll(reimbList.get());
        }
        //return the list with an ok status code
        return ResponseEntity.ok(pendingList);
    }

    //function for getting approved or declined reimbursements depending on passed bool
    //true = approved, false = declined
    @GetMapping("/approvedreimbursements")
    @AdminOnly
    public ResponseEntity<List<Reimbursement>> getApprovedReimbursements(@RequestParam boolean approved){
        Optional<List<Reimbursement>> reimbList = reimbursementService.findByApproved(approved);


        //this code is the same as pending reimbursement code
        //list for returning
        List pendingList = new ArrayList<Reimbursement>();

        //check if there are any reimbursements meeting the req
        if (reimbList.isEmpty()){
            return ResponseEntity.ok(pendingList);
        } else {
            //could use this or a for each loop, shouldnt matter
            pendingList.addAll(reimbList.get());
        }


        //return the list with an ok status code
        return ResponseEntity.ok(pendingList);
    }

    @GetMapping("/userreimbursements")
    @AdminOnly
    public ResponseEntity<List<Reimbursement>> getUserReimbursements(@RequestParam int userId){
        Optional<List<Reimbursement>> reimbList = reimbursementService.findByUserId(userId);


        //this code is the same as pending reimbursement code
        //list for returning
        List userReimbList = new ArrayList<Reimbursement>();

        //check if there are any reimbursements meeting the req
        if (reimbList.isEmpty()){
            return ResponseEntity.ok(userReimbList);
        } else {
            //could use this or a for each loop, shouldnt matter
            userReimbList.addAll(reimbList.get());
        }

        //return the list with an ok status code
        return ResponseEntity.ok(userReimbList);
    }





}
