package com.enigma.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_loan_type")
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Double maxLoan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMaxLoan() {
        return maxLoan;
    }

    public void setMaxLoan(Double maxLoan) {
        this.maxLoan = maxLoan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
