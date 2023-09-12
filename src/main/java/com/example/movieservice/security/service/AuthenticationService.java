package com.example.movieservice.security.service;

import com.example.movieservice.model.Role;
import com.example.movieservice.model.dto.AuthenticationDto;
import com.example.movieservice.model.dto.AuthenticationResponseDto;
import com.example.movieservice.model.UserEntity;
import com.example.movieservice.model.dto.UserDto;
import com.example.movieservice.repository.RoleRepository;
import com.example.movieservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    public AuthenticationResponseDto register(UserDto userDto) {
       UserEntity userEntity=new UserEntity();
       userEntity.setFirstName(userDto.firstName());
       userEntity.setLastName(userDto.lastName());
       userEntity.setUsername(userDto.username());
       userEntity.setPassword(passwordEncoder.encode(userDto.password()));
       for(int i:userDto.roles()){
           Optional<Role> optionalRole=roleRepository.findById(i);
           if(optionalRole.isPresent()){
               Role role=optionalRole.get();
               userEntity.getRoles().add(role);
           }
       }
       userRepository.save(userEntity);
       var jwtToken=jwtService.generateToken(userEntity);
       return new AuthenticationResponseDto(jwtToken);
    }

    public AuthenticationResponseDto authenticate(AuthenticationDto request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),request.password()));
        var user=userRepository.findByUsername(request.username()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDto(jwtToken);
    }


}
