package com.example.movieservice.repository;

import com.example.movieservice.model.Authority;
import com.example.movieservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(Authority name);
}
