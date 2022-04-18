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

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtValidatorImpl implements JwtValidator {

  private static JwtValidatorImpl instance;
  private static JWTVerifier jwtVerifier;

  public static JwtValidatorImpl getInstance(@NonNull String secret, @NonNull String issuer) {
    if (instance == null) {
      instance = new JwtValidatorImpl();
    }
    jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer(issuer)
        .build();
    return instance;
  }

  @Override
  public DecodedJWT checkJwtToken(@NonNull String token) throws JWTVerificationException {
    DecodedJWT verify = jwtVerifier.verify(token);
    if (!verify.getClaims().containsKey("email") || !verify.getClaims().containsKey("password")) {
      throw new JWTVerificationException("No required fields in the JWT");
    }
    return verify;
  }
}
