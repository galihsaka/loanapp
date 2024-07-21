package com.enigma.dto.request;

public class RejectTransactionLoanRequest {
    private String loanTransactionId;

    public String getLoanTransactionId() {
        return loanTransactionId;
    }

    public void setLoanTransactionId(String loanTransactionId) {
        this.loanTransactionId = loanTransactionId;
    }
}
