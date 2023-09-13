package com.example.movieservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserDto(@NotBlank(message = "FirstName can not be empty")
                      String firstName,
                      @NotBlank(message = "LastName can not be empty")
                      String lastName,
                      @NotNull(message = "Username can not be null")
                      String username,
                      @NotBlank(message = "Password can not be empty")
                      String password,
                      @Size(min = 1,message = "You should choose at least one role")
                      List<String> roles) {
}
