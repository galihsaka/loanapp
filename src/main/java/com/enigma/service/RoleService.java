package com.enigma.service;

import com.enigma.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getOrSave(List<Role.ERole> roles);
}
