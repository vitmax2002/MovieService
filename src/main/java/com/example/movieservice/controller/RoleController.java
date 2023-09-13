package com.example.movieservice.controller;

import com.example.movieservice.model.Role;
import com.example.movieservice.security.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> addRole(@Valid @RequestBody Role role){
        Role createdRole=roleService.addRole(role);
        return ResponseEntity.ok().body(createdRole);
    }
}
