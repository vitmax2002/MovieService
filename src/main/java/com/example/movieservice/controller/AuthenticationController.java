package com.example.movieservice.controller;

import com.example.movieservice.model.dto.AuthenticationDto;
import com.example.movieservice.model.dto.AuthenticationResponseDto;
import com.example.movieservice.model.dto.UserDto;
import com.example.movieservice.security.service.AuthenticationService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@Valid @RequestBody UserDto userDto){
        AuthenticationResponseDto authenticationResponseDto=authenticationService.register(userDto);
        return ResponseEntity.ok().body(authenticationResponseDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@Valid @RequestBody AuthenticationDto authenticationDto){
        AuthenticationResponseDto authenticationResponseDto=authenticationService.authenticate(authenticationDto);
        return ResponseEntity.ok().body(authenticationResponseDto);
    }
}
