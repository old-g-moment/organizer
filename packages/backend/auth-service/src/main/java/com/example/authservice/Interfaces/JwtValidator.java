package com.example.authservice.Interfaces;

import lombok.NonNull;

public interface JwtValidator {

  boolean checkJwtToken(@NonNull String token, @NonNull String secret, @NonNull String issue);
}
