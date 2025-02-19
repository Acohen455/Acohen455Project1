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


    //constructors
    public Reimbursement() {
    }

    public Reimbursement(int reimbursementId, String description, double amount, boolean pending, int userId) {
        this.reimbursementId = reimbursementId;
        this.description = description;
        this.amount = amount;
        this.pending = pending;
        this.userId = userId;
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

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //toString
    //TODO: Customize this later


    @java.lang.Override
    public java.lang.String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", pending=" + pending +
                ", userId=" + userId +
                '}';
    }
}