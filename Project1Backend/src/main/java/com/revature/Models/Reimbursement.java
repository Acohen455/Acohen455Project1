package com.revature.models;



@Component
@Entity
@Table(name = "reimbursements")
public class Reimbursement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbursementId;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", columnDefinition = "DECIMAL(10,2)")
    private double amount;

    //making the status boolean -- false = pending, true = approved
    //going to rename this column to pending
    //this used to be the status column
    //since I want to use booleans here, pending is a better naming scheme
    @Column(name = "pending", columnDefinition = "boolean")
    private boolean pending;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private int userId;




}