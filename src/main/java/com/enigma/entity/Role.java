package com.enigma.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ERole role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public enum ERole {
        ROLE_CUSTOMER,
        ROLE_STAFF,
        ROLE_ADMIN
    }
}
