package com.example.authservice.JwtAuth;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authservice.Interfaces.JwtDataGetter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public Map<String, String> getCoreData(@NotNull DecodedJWT token)
      throws IllegalAccessException {
    final Claim email = token.getClaim("email");
    final Claim password = token.getClaim("password");

    if (email.asString().isEmpty() || password.asString().isEmpty()) {
      throw new IllegalAccessException();
    }
    return new HashMap<>() {{
      putIfAbsent("email", email.asString());
      putIfAbsent("password", password.asString());
    }};
  }

  @Override
  public List<String> getDataFromClaims(@NotNull DecodedJWT token,
      @NotNull String... claims)
      throws IllegalArgumentException, IllegalAccessException {
    if (claims.length == 0) {
      throw new IllegalArgumentException();
    }
    final List<String> data = new ArrayList<>();
    for (String element :
        claims) {
      if (!token.getClaims().containsKey(element)) {
        throw new IllegalAccessException();
      }
      data.add(token.getClaim(element).asString());
    }
    return data;
  }
}
