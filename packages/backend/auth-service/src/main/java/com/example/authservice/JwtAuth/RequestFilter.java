package com.example.authservice.JwtAuth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class RequestFilter extends OncePerRequestFilter {

  @Value("${envs.secret}")
  private String secret;
  @Value("${envs.issuer}")
  private String issuer;

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain) throws ServletException, IOException {
    if (request.getHeader(HttpHeaders.AUTHORIZATION) == null || !request.getHeader(
        HttpHeaders.AUTHORIZATION).startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];

    try {
      DecodedJWT decodedJWT = JwtValidatorImpl.getInstance(secret, issuer).checkJwtToken(token);
      Map<String, String> coreData = JwtDataGetterImpl.getJwtDataGetter().getCoreData(decodedJWT);
      System.out.println(coreData);
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken("1", null, null);
      usernamePasswordAuthenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      filterChain.doFilter(request, response);
    } catch (final JWTVerificationException | IllegalArgumentException | IllegalAccessException error) {
      error.printStackTrace();
      filterChain.doFilter(request, response);
    }
  }
}
