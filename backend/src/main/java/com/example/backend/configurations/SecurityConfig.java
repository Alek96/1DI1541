package com.example.backend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http,
      CorsConfigurationSource corsConfigurationSource) throws Exception {

    http
        // Enable and configure CORS
        .cors(cors -> cors.configurationSource(corsConfigurationSource))

        // State-less session (state in access-token only)
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        // Disable CSRF because of disabled sessions
        .csrf(csrf -> csrf.disable());

    return http.build();
  }
}
