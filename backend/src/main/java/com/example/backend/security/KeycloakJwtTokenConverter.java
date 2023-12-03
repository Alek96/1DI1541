package com.example.backend.security;


import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakJwtTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private final KeycloakJwtAuthoritiesConverter authoritiesConverter;

  @Override
  public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
    return new JwtAuthenticationToken(jwt, authoritiesConverter.convert(jwt),
        getPrincipalClaimName(jwt));
  }

  private String getPrincipalClaimName(Jwt jwt) {
    return jwt.getClaim("preferred_username");
  }
}
