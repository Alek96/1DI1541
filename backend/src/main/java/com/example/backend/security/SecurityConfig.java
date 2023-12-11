package com.example.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final KeycloakJwtTokenConverter jwtTokenConverter;
  private final CorsConfigurationSource corsConfigurationSource;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        // Secure all routes but actuator and Swagger-UI
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(
                "/actuator/health",
                "/notes",
                "/swagger-ui/**",
                "/v3/api-docs/**").permitAll()
            .anyRequest().authenticated()
        )

        // Enable OAuth2 with custom authorities mapping
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtTokenConverter))
        )

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
