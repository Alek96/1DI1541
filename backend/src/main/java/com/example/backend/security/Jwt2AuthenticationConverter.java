package com.example.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Jwt2AuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private final Jwt2AuthoritiesConverter authoritiesConverter;

  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    return new JwtAuthenticationToken(jwt, authoritiesConverter.convert(jwt), getPrincipalClaimName(jwt));
  }

  private String getPrincipalClaimName(Jwt jwt) {
    return jwt.getClaim("preferred_username");
  }
}
