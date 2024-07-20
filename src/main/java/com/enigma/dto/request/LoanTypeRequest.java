package com.enigma.dto.request;

public class LoanTypeRequest {
    private String type;
    private Double maxLoan;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMaxLoan() {
        return maxLoan;
    }

    public void setMaxLoan(Double maxLoan) {
        this.maxLoan = maxLoan;
    }
}
