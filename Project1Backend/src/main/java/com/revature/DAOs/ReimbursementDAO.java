package com.revature.DAOs;

import com.revature.models.Reimbursement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Transactional
    @Query("UPDATE Reimbursement r SET r.description = :description WHERE r.reimbursementId = :reimbursementId")
    void updateReimbursementDescriptionByReimbursementId(@Param("description") String description,
                                                         @Param("reimbursementId") Integer reimbursementId);

    @Modifying
    @Transactional
    @Query("UPDATE Reimbursement r SET r.pending = :pending, r.approved = :approved WHERE r.reimbursementId = :reimbursementId")
    void setReimbursementStatusAndReimbursementApprovedByReimbursementId(@Param("pending") boolean status,
                                                                                            @Param("approved") boolean approved,
                                                                                            @Param("reimbursementId") Integer reimbursementId);


}