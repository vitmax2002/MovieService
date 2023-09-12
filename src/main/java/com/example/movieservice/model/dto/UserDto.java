package com.example.movieservice.model.dto;

import java.util.List;

public record UserDto(String firstName,
                      String lastName,
                      String username,
                      String password,
                      List<String> roles) {
}
