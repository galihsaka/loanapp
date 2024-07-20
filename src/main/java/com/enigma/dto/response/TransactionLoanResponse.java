package com.enigma.dto.response;

import com.enigma.entity.TransactionLoan;
import com.enigma.entity.TransactionLoanDetails;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TransactionLoanResponse {
    private String id;
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;
    private Double nominal;
    private TransactionLoanDetailsResponse transactionLoanDetailsResponse;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date approvedAt;
    private String approvedBy;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date rejectedAt;
    private String rejectedBy;
    private TransactionLoan.ApprovalStatus approvalStatus;
    @JsonFormat(pattern="yyyy-MM-dd")// enum
    private Date createdAt;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    public TransactionLoanDetailsResponse getTransactionLoanDetailsResponse() {
        return transactionLoanDetailsResponse;
    }

    public void setTransactionLoanDetailsResponse(TransactionLoanDetailsResponse transactionLoanDetailsResponse) {
        this.transactionLoanDetailsResponse = transactionLoanDetailsResponse;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getInstalmentTypeId() {
        return instalmentTypeId;
    }

    public void setInstalmentTypeId(String instalmentTypeId) {
        this.instalmentTypeId = instalmentTypeId;
    }

    public String getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(String loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(Date rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public TransactionLoan.ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(TransactionLoan.ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(String rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
