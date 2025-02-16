package com.revature.services;


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
    public List<Reimbursement> findByUserId(Integer userId) {
        return reimbursementDAO.findAllByUserId(userId);
    }

    //grabs a single reimbursement by ID
    public Reimbursement findByReimbursementId(Integer reimbursementId) {
        return reimbursementDAO.findByReimbursementId(reimbursementId);
    }


    //TODO: Implement methods to search reimbursements by amount
}