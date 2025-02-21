package com.revature.services;


import com.revature.DAOs.ReimbursementDAO;
import com.revature.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReimbursementService {

    //we'll use constructor injection for this, no need for field injection
    private final ReimbursementDAO reimbursementDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }


    //we want to find the reimbursement by id or user id
    //coding by userid first
    public Optional<List<Reimbursement>> findByUserId(Integer userId) {
        return reimbursementDAO.findAllByUserUserId(userId);
    }

    //grabs a single reimbursement by ID
    public Optional<Reimbursement> findByReimbursementId(Integer reimbursementId) {
        return reimbursementDAO.findByReimbursementId(reimbursementId);
    }

    public Optional<List<Reimbursement>> findByStatusAndUserId(boolean status, Integer userId) {
        return reimbursementDAO.findByPendingAndUserUserId(status, userId);
    }

    //function to create a reimbursement
    public Reimbursement createReimbursement(Reimbursement reimbursement) {
        Reimbursement toSave = new Reimbursement();

        if (!(toSave.getDescription() == null)){
            toSave.setDescription(reimbursement.getDescription());
        }

        //we'll do input validation on the frontend instead of sending stuff back and forth
        //easier to do it this way, we can just pop an alert or something if invalid
        toSave.setAmount(reimbursement.getAmount());
        toSave.setPending(reimbursement.isPending());
        toSave.setUser(reimbursement.getUser());


        return reimbursementDAO.save(reimbursement);
    }




    //TODO: Implement methods to search reimbursements by amount
}