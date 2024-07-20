package com.enigma.dto.response;

public class LoanTypeResponse {
    private String id;
    private String type;
    private Double maxLoan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
