package com.example.keycloakInitializer.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AdminConfig {

  private final KeycloakProperties keycloakProperties;

  @Bean
  public Keycloak keycloakAdmin() {
    return KeycloakBuilder.builder()
        .serverUrl(keycloakProperties.getServerUrl())
        .realm("master")
        .username("admin")
        .password("admin")
        .clientId("admin-cli")
        .build();
  }
}
