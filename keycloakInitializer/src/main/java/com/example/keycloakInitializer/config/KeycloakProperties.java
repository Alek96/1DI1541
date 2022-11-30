package com.example.keycloakInitializer.config;

import com.example.keycloakInitializer.security.Role;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.keycloak")
public class KeycloakProperties {

  private String serverUrl;
  private String realmName;
  private List<Client> clients;
  private List<User> users;

  @Data
  public static class Client {

    private String id;
    private String redirectUrl;
  }

  @Data
  public static class User {

    private String username;
    private String password;
    private List<Role> roles;

    public List<String> getRoles() {
      return roles.stream()
          .map(Role::toString)
          .toList();
    }
  }
}
