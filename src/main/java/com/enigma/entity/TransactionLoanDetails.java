package com.enigma.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "trx_loan_details")
public class TransactionLoanDetails {
    public enum LoanStatus {
        PAID,
        UNPAID
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date transactionDate;
    private Double nominal;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "trx_loan_id")
//    private TransactionLoan loanTransaction;
    @ManyToOne
    @JoinColumn(name = "guarantee_picture_id")
    private GuaranteePicture guaranteePicture;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus; // enum
    private Date createdAt;
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public GuaranteePicture getGuaranteePicture() {
        return guaranteePicture;
    }

    public void setGuaranteePicture(GuaranteePicture guaranteePicture) {
        this.guaranteePicture = guaranteePicture;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

//    public TransactionLoan getLoanTransaction() {
//        return loanTransaction;
//    }
//
//    public void setLoanTransaction(TransactionLoan loanTransaction) {
//        this.loanTransaction = loanTransaction;
//    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
