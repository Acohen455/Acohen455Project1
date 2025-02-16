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

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private int userId;




}