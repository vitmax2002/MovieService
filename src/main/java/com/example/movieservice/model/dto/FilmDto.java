package com.example.movieservice.model.dto;

import com.example.movieservice.model.Actor;
import com.example.movieservice.model.Language;
import com.example.movieservice.model.Rating;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;
import java.util.List;

public record FilmDto(@NotBlank(message = "You should give a title for movie")
                      String title,
                      @NotNull(message = "You should give year")
                      Year year,
                      @Min(value =1, message = "you should give the length of movie")
                      int length,
                      @Min(value =1, message = "Give number of places for film")
                      int places,
                      @NotNull(message = "Choose the rating of film LOW,MEDIUM or HIGH")
                      @Enumerated(EnumType.STRING)
                      Rating rating,
                      @NotNull(message = "Give at least one language for film")
                      List<String> languages,
                      @NotNull(message = "Give at least one actor for film")
                      List<String> actors
                      ) {
}
