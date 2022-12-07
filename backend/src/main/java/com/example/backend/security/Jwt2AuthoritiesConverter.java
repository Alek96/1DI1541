package com.example.backend.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class Jwt2AuthoritiesConverter implements Converter<Jwt, Collection<? extends GrantedAuthority>> {

  private static final String REALM_ACCESS = "realm_access";
  private static final String RESOURCE_ACCESS = "resource_access";
  private static final String ROLES = "roles";

  @Value("${app.jwt.resource-client-ids}")
  private List<String> clientIds;

  @Override
  @SuppressWarnings("unchecked")
  public Collection<? extends GrantedAuthority> convert(Jwt jwt) {

    var realmAccess = (Map<String, Object>) jwt.getClaims().getOrDefault(REALM_ACCESS, Map.of());
    var realmRoles = (Collection<String>) realmAccess.getOrDefault(ROLES, List.of());

    var resourceAccess = (Map<String, Object>) jwt.getClaims().getOrDefault(RESOURCE_ACCESS, Map.of());
    var clientRoles = clientIds.stream()
        .map(clientId -> (Map<String, Object>) resourceAccess.getOrDefault(clientId, Map.of()))
        .map(client -> (Collection<String>) client.getOrDefault(ROLES, List.of()))
        .flatMap(Collection::stream)
        .toList();

    return Stream.concat(realmRoles.stream(), clientRoles.stream())
        .map(SimpleGrantedAuthority::new)
        .toList();
  }
}
