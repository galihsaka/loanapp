package com.enigma.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_instalment_type")
public class InstalmentType {
    public enum EInstalmentType {
        ONE_MONTH,
        THREE_MONTHS,
        SIXTH_MONTHS,
        NINE_MONTHS,
        TWELVE_MONTHS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EInstalmentType instalmentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EInstalmentType getInstalmentType() {
        return instalmentType;
    }

    public void setInstalmentType(EInstalmentType instalmentType) {
        this.instalmentType = instalmentType;
    }
}
