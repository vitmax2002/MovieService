package com.example.movieservice.repository;

import com.example.movieservice.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
}
