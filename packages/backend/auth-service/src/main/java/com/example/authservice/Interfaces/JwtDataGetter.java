package com.example.authservice.Interfaces;

import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface JwtDataGetter {

  Map<String, String> getCoreData(@NotNull DecodedJWT token)
      throws IllegalAccessException;

  List<String> getDataFromClaims(@NotNull DecodedJWT token,
      @NotNull String... claims) throws IllegalArgumentException, IllegalAccessException;
}
