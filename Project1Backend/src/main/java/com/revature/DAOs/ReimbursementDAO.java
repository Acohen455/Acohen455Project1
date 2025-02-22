package com.revature.DAOs;

import com.revature.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer> {
    //we want to find the reimbursement by id or user id
    //write method stubs for this and spring does the rest
    Optional<Reimbursement> findByReimbursementId(Integer reimbursementId);

    Optional<List<Reimbursement>> findAllByUserUserId(int userId);

    Optional<List<Reimbursement>> findByPendingAndUserUserId(boolean status, Integer userId);

    Optional<List<Reimbursement>> findByPending(boolean status);

    Optional<List<Reimbursement>> findByApproved(boolean status);

    Optional<Reimbursement> updateReimbursementDescriptionByReimbursementId(String description, Integer reimbursementId);

    Optional<Reimbursement> setReimbursementStatusAndReimbursementApprovedByReimbursementId(boolean status, boolean approved, Integer reimbursementId);


}