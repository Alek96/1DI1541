package com.example.keycloakInitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.keycloakInitializer.config")
public class KeycloakInitializerApplication {

  public static void main(String[] args) {
    SpringApplication.run(KeycloakInitializerApplication.class, args);
  }

}
