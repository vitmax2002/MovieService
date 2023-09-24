package com.example.movieservice.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record FilmCategoryDto(@Min(value=1,message = "You should give a film id")
                              int filmId,
                              @Min(value=1,message = "You should give a category id")
                              int categoryId,
                              LocalDateTime lastUpdate) {
}
