package com.example.authservice.JwtAuth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authservice.Interfaces.JwtValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class JwtValidatorImpl implements JwtValidator {
  private static JwtValidatorImpl instance;

  public static JwtValidatorImpl getInstance() {
    if (instance == null) {
      instance = new JwtValidatorImpl();
    }
    return instance;
  }

  @Override
  public boolean checkJwtToken(@NonNull String token, @NonNull String secret, @NonNull String issuer) {
    log.info("ISSUER VALUE: " + issuer);
    final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer(issuer).build();
    try {
      DecodedJWT verify = jwtVerifier.verify(token);
      System.out.println(verify.getClaims());
    } catch (final JWTVerificationException error) {
      error.printStackTrace();
      return false;
    }
    return true;
  }
}
