package com.example.backend.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Slf4j
@Configuration
public class JwtConfig {

  @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
  private String jwkSetUri;

  /**
   * Create JwtDecoder with custom Validator that will not validate issuer url
   */
  @Bean
  public JwtDecoder jwtDecoder() {
    log.info("Initialize JwtDecoder with {}", jwkSetUri);
    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri)
        .build();

    OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefault();
    jwtDecoder.setJwtValidator(validator);

    return jwtDecoder;
  }

  /**
   * Used to extracts the GrantedAuthorities from scope attributes typically found in a Jwt.
   */
  @Bean
  public JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter() {
    return new JwtGrantedAuthoritiesConverter();
  }
}
