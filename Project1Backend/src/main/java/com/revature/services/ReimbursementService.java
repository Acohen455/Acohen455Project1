package com.revature.services;


import com.revature.DAOs.ReimbursementDAO;
import com.revature.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public Optional<Reimbursement> findByReimbursementId(int reimbursementId) {
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

    //get reimbursements by status
    public Optional<List<Reimbursement>> findByStatus(boolean status) {
        return reimbursementDAO.findByPending(status);
    }

    public Optional<List<Reimbursement>> findByApproved(boolean status) {
        return reimbursementDAO.findByApproved(status);
    }


    public void updateReimbursementDescription(String description, Integer reimbursementId) {
        reimbursementDAO.updateReimbursementDescriptionByReimbursementId(description, reimbursementId);
    }

    public List<Reimbursement> getAllReimbursements() {
        //should return an empty list if none found
        return reimbursementDAO.findAll();
    }

    //TODO: Implement on the frontend as a drop down with a nested dropdown
    public void updateReimbursementStatusAndApproval(Integer reimbursementId, boolean status, boolean approved) {
        //this is incredibly wordy and ugly, but this is the only way to do it so spring boot knows how to wire it i think
        //this is also cleaner than doing it in multiple methods imo
        //parameter list in the DAO is status, approval status, reimbursement id
        reimbursementDAO.setReimbursementStatusAndReimbursementApprovedByReimbursementId(status, approved, reimbursementId);
    }



    //sort an existing list of reimbursements by status
    //returns the list of reimbu
    //DEPRECATED CODE -- DONT NEED THIS BUT LEAVING FOR POSTERITY
    /*
    public List<Reimbursement> sortReimbursementsByStatus(List<Reimbursement> reimbursements, boolean pendingStatus) {
        List<Reimbursement> pendingReimbs = new ArrayList<>();

        if (pendingStatus){
            for(Reimbursement reimb : reimbursements) {
                if (reimb.isPending()) {
                    pendingReimbs.add(reimb);
                }
            }
        } else {
            for(Reimbursement reimb : reimbursements) {
                if (!reimb.isPending()) {
                    pendingReimbs.add(reimb);
                }
            }
        }

        return pendingReimbs;
    }
    */





    //TODO: Implement methods to search reimbursements by amount
}