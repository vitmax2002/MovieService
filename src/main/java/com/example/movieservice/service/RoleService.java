package com.example.movieservice.service;

import com.example.movieservice.model.Role;
import com.example.movieservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(Role role){
        Role createdRole=roleRepository.save(role);
        return createdRole;
    }
}
