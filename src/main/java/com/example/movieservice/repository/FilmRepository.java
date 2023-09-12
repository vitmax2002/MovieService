package com.example.movieservice.repository;

import com.example.movieservice.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Integer> {
}
