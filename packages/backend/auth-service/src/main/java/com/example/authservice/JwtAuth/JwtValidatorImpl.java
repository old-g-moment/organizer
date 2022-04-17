package com.example.authservice.JwtAuth;

import com.example.authservice.Interfaces.JwtValidator;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class JwtValidatorImpl implements JwtValidator {

  private final String issuer;

  public JwtValidatorImpl(@Value("${envs.issuer}") String issuer) {
    this.issuer = issuer;
  }

  @Override
  public boolean checkJwtToken(@NonNull String token) {
    log.info("ISSUER VALUE: " + issuer);
    return true;
  }
}
