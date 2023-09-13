package com.example.movieservice.model.dto;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDto(@NotNull(message = "UserName can not be empty")
                                String username,
                                @NotNull(message = "Password can not be empty")
                                String password) {
}
