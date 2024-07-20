package com.enigma.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "trx_loan")
public class TransactionLoan {
    public enum ApprovalStatus {
        APPROVED,
        REJECTED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;
    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(nullable = false)
    private Double nominal;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trx_loan_detail_id")
    private TransactionLoanDetails transactionLoanDetails;
    private Date approvedAt;
    private String approvedBy;
    private Date rejectedAt;
    private String rejectedBy;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus; // enum
    private Date createdAt;
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionLoanDetails getTransactionLoanDetails() {
        return transactionLoanDetails;
    }

    public void setTransactionLoanDetails(TransactionLoanDetails transactionLoanDetails) {
        this.transactionLoanDetails = transactionLoanDetails;
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

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(String rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    public Date getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(Date rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InstalmentType getInstalmentType() {
        return instalmentType;
    }

    public void setInstalmentType(InstalmentType instalmentType) {
        this.instalmentType = instalmentType;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}
