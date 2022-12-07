package com.example.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final Jwt2AuthenticationConverter authenticationConverter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // Route security: authenticated to all routes but actuator and Swagger-UI
    http.authorizeRequests()
        .antMatchers("/actuator/health").permitAll()
        .anyRequest().authenticated();

    // Enable OAuth2 with custom authorities mapping
    http.oauth2ResourceServer()
        .jwt()
        .jwtAuthenticationConverter(authenticationConverter);

    // Enable and configure CORS
    http.cors();

    // State-less session (state in access-token only)
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Disable CSRF because of disabled sessions
    http.csrf().disable();

    return http.build();
  }
}
