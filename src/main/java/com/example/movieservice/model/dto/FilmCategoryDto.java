package com.example.movieservice.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record FilmCategoryDto(@NotBlank(message = "You should give a film id")
                              int filmId,
                              @NotBlank(message = "You should give a category id")
                              int categoryId,
                              LocalDateTime lastUpdate) {
}
