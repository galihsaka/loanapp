package com.enigma.dto.request;

import com.enigma.entity.Role;

import java.util.List;

public class RegisterRequest {
    private String username;
    private String password;
    private List<Role.ERole> roles;

    public List<Role.ERole> getRoles() {
        return roles;
    }

    public void setRoles(List<Role.ERole> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
