package com.example.authservice.JWTValidatorTests;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.authservice.JwtAuth.JwtValidatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JWTValidatorTests {

  @Test
  public void checkTokenValidation() {
    assertNotNull(JwtValidatorImpl.getInstance("test-secret", "test-issuer").checkJwtToken(
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJpc3MiOiJ0ZXN0LWlzc3VlciIsImVtYWlsIjoiam9obmRvZUBnbWFpbC5jb20iLCJwYXNzd29yZCI6IjEyMzQifQ.hNxoo-GfwF-WkxNrD-ocgB5w7vlccap_HIRA2oSO3Yw"));
    assertThrows(JWTVerificationException.class, () ->
      JwtValidatorImpl.getInstance("test-secret", "test-issuer").checkJwtToken(
          "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJpc3MiOiJ0ZXN0LWlzc3VlciJ9.ZcnQ-t7QirNqNIlbZek6lRicI4JVgSIVnFhhRgvKOi4")
    );
  }
}
