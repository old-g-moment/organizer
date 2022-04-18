package com.example.authservice.Interfaces;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NonNull;

public interface JwtValidator {

  DecodedJWT checkJwtToken(@NonNull String token) throws JWTVerificationException;
}
