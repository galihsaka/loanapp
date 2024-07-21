package com.enigma.dto.request;

public class ApproveTransactionLoanRequest {
    private String loanTransactionId;
    private Double interestRates;

    public String getLoanTransactionId() {
        return loanTransactionId;
    }

    public void setLoanTransactionId(String loanTransactionId) {
        this.loanTransactionId = loanTransactionId;
    }

    public Double getInterestRates() {
        return interestRates;
    }

    public void setInterestRates(Double interestRates) {
        this.interestRates = interestRates;
    }
}
