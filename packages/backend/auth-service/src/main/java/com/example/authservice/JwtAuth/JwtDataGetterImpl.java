package com.example.authservice.JwtAuth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authservice.Interfaces.JwtDataGetter;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtDataGetterImpl implements JwtDataGetter {

  private static JwtDataGetterImpl jwtDataGetter;

  public static JwtDataGetterImpl getJwtDataGetter() {
    if (jwtDataGetter == null) {
      jwtDataGetter = new JwtDataGetterImpl();
    }
    return jwtDataGetter;
  }

  @Override
  public String getEmailFromJWT(@NotNull DecodedJWT token)
      throws IllegalAccessException {
    final String email = token.getClaim("email").asString();

    if (email.isEmpty()) {
      throw new IllegalAccessException();
    }
    return email;
  }

  @Override
  public List<String> getDataFromClaims(@NotNull DecodedJWT token,
      @NotNull String... claims)
      throws IllegalArgumentException, IllegalAccessException {
    if (claims.length == 0) {
      throw new IllegalArgumentException();
    }
    final List<String> data = new ArrayList<>();
    for (String element : claims) {
      if (!token.getClaims().containsKey(element)) {
        throw new IllegalAccessException();
      }
      data.add(token.getClaim(element).asString());
    }
    return data;
  }
}
