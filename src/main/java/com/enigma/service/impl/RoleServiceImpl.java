package com.enigma.service.impl;

import com.enigma.entity.Role;
import com.enigma.repository.RoleRepository;
import com.enigma.service.RoleService;
import com.enigma.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getOrSave(List<Role.ERole> roles) {
        List<Role> roleList=new ArrayList<>();
        for(int i=0;i< roles.size();i++){
            Optional<Role> optionalRole = roleRepository.findByRole(roles.get(i));
            if (optionalRole.isEmpty()) {
                Role roleSaved=new Role();
                roleSaved.setRole(roles.get(i));
                roleRepository.save(roleSaved);
            }
        }
        for(int j=0;j< roles.size();j++){
            Optional<Role> optionalRole = roleRepository.findByRole(roles.get(j));
            Role newRole=optionalRole.orElseThrow(()->new ResourceNotFoundException("Role Not Found"));
            roleList.add(newRole);
        }
        return roleList;
    }
}
