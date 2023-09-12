package com.example.movieservice.model.dto;

import java.time.LocalDateTime;

public record FilmCategoryDto(int filmId,
                              int categoryId,
                              LocalDateTime lastUpdate) {
}
