package com.revature.DAOs;

import com.revature.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer> {
    //we want to find the reimbursement by id or user id
    //write method stubs for this and spring does the rest
    Optional<Reimbursement> findByReimbursementId(Integer reimbursementId);

    Optional<List<Reimbursement>> findAllByUserId(Integer userId);

    Optional<List<Reimbursement>> findByStatusAndUserId(boolean status, Integer userId);


}