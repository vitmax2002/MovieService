package com.example.movieservice.security.config;

import com.example.movieservice.model.Role;
import com.example.movieservice.model.Roles;
import com.example.movieservice.security.service.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/actors","/films","/categories",
                        "/languages","/filmcategory","/roles").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers(HttpMethod.DELETE,"/actors/**","/films/**","/categories/**",
                        "/languages/**","/filmcategory/**","/roles/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers(HttpMethod.PUT,"/actors/**","/films/**","/categories/**",
                        "/languages/**","/filmcategory/**","/roles/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers(HttpMethod.GET,"/actors/**","/films/**","/categories/**",
                        "/languages/**","/filmcategory/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
