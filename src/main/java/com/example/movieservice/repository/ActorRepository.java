package com.example.movieservice.repository;

import com.example.movieservice.model.Actor;
import com.example.movieservice.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Integer> {

    Actor findByFirstName(String firstName);
    boolean existsByFirstName(String firstName);
}
