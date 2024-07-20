package com.enigma.dto.request;


public class TransactionLoanRequest {
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;
    private Double nominal;

    public String getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(String loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getInstalmentTypeId() {
        return instalmentTypeId;
    }

    public void setInstalmentTypeId(String instalmentTypeId) {
        this.instalmentTypeId = instalmentTypeId;
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
}
