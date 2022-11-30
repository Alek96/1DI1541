package com.example.keycloakInitializer.runner;

import com.example.keycloakInitializer.config.KeycloakProperties;
import com.example.keycloakInitializer.config.KeycloakProperties.Client;
import com.example.keycloakInitializer.config.KeycloakProperties.User;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeycloakInitializer implements CommandLineRunner {

  private final Keycloak keycloakAdmin;
  private final KeycloakProperties keycloakProperties;


  @Override
  public void run(String... args) {
    log.info("Initializing '{}' realm in Keycloak ...", keycloakProperties.getRealmName());
    deleteRealmConfiguration();

    // Realm
    RealmRepresentation realmRepresentation = new RealmRepresentation();
    realmRepresentation.setRealm(keycloakProperties.getRealmName());
    realmRepresentation.setEnabled(true);
    realmRepresentation.setRegistrationAllowed(true);

    // Clients
    List<ClientRepresentation> clients = keycloakProperties.getClients().stream()
        .map(client -> {
          ClientRepresentation clientRepresentation = new ClientRepresentation();
          clientRepresentation.setClientId(client.getId());
          clientRepresentation.setDirectAccessGrantsEnabled(true);
          clientRepresentation.setPublicClient(true);
          clientRepresentation.setRedirectUris(Collections.singletonList(client.getRedirectUrl()));

          return clientRepresentation;
        })
        .toList();
    realmRepresentation.setClients(clients);

    // Users
    List<UserRepresentation> userRepresentations = keycloakProperties.getUsers().stream()
        .map(user -> {
          // User Credentials
          CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
          credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
          credentialRepresentation.setValue(user.getPassword());

          // User
          UserRepresentation userRepresentation = new UserRepresentation();
          userRepresentation.setUsername(user.getUsername());
          userRepresentation.setEnabled(true);
          userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
          Map<String, List<String>> clientRoles = keycloakProperties.getClients().stream()
              .map(Client::getId)
              .collect(Collectors.toMap(k -> k, v -> user.getRoles()));
          userRepresentation.setClientRoles(clientRoles);

          return userRepresentation;
        })
        .toList();
    realmRepresentation.setUsers(userRepresentations);

    // Create Realm
    keycloakAdmin.realms().create(realmRepresentation);

    // Testing
    createTokenTest();
  }

  private void deleteRealmConfiguration() {
    Optional<RealmRepresentation> representationOptional = keycloakAdmin.realms()
        .findAll()
        .stream()
        .filter(r -> r.getRealm().equals(keycloakProperties.getRealmName()))
        .findAny();
    if (representationOptional.isPresent()) {
      log.info("Removing already pre-configured '{}' realm", keycloakProperties.getRealmName());
      keycloakAdmin.realm(keycloakProperties.getRealmName()).remove();
    }
  }

  private void createTokenTest() {
    User user = keycloakProperties.getUsers().get(0);
    log.info("Testing getting token for '{}' ...", user.getUsername());

    try (Keycloak keycloakMovieApp = KeycloakBuilder.builder()
        .serverUrl(keycloakProperties.getServerUrl())
        .realm(keycloakProperties.getRealmName())
        .username(user.getUsername())
        .password(user.getPassword())
        .clientId(keycloakProperties.getClients().get(0).getId())
        .build()) {

      log.info("'{}' token: {}", user.getUsername(), keycloakMovieApp.tokenManager().grantToken().getToken());
      log.info("'{}' initialization completed successfully!", keycloakProperties.getRealmName());
    }
  }
}
