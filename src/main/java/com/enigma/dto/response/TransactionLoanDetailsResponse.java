package com.enigma.dto.response;

import com.enigma.entity.GuaranteePicture;
import com.enigma.entity.TransactionLoanDetails;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TransactionLoanDetailsResponse {
    private String id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date transactionDate;
    private Double nominal;
    private TransactionLoanDetails.LoanStatus loanStatus;
    private GuaranteePicture guaranteePicture;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    public GuaranteePicture getGuaranteePicture() {
        return guaranteePicture;
    }

    public void setGuaranteePicture(GuaranteePicture guaranteePicture) {
        this.guaranteePicture = guaranteePicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionLoanDetails.LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(TransactionLoanDetails.LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
