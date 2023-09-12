package com.example.movieservice.repository;

import com.example.movieservice.model.FilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory,Integer> {
}
