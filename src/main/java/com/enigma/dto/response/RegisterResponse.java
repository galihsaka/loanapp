package com.enigma.dto.response;

import com.enigma.entity.Role;

import java.util.List;

public class RegisterResponse {
    private String username;
    private List<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
