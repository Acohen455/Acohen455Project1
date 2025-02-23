package com.revature.models;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "reimbursements")
public class Reimbursement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbursementId;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", columnDefinition = "DECIMAL(10,2)", nullable = false)
    private double amount;

    //making the status boolean -- true = pending, false = approved
    //going to rename this column to pending
    //this used to be the status column
    //since I want to use booleans here, pending is a better naming scheme
    @Column(name = "status", columnDefinition = "text", nullable = false)
    private String status;

    //cascade is already defined in the user entity
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;


    //constructors
    public Reimbursement() {
    }

    public Reimbursement(int reimbursementId, String description, double amount, String status, boolean approved, User user) {
        this.reimbursementId = reimbursementId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }


    //setters and getters
    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    //toString
    //TODO: Customize this later
    @java.lang.Override
    public java.lang.String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", pending=" + status +
                ", user=" + user.toString() +
                '}';
    }
}