package com.example.authservice.JwtAuth;

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
//  public RequestFilter(@Value("${envs.secret}") String secret,
//      @Value("${envs.issuer}") String issuer) {
//    jwtValidator = JwtValidatorImpl.getInstance();
//  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain) throws ServletException, IOException {
    if (request.getHeader(HttpHeaders.AUTHORIZATION) == null || !request.getHeader(
        HttpHeaders.AUTHORIZATION).startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];

    if (!JwtValidatorImpl.getInstance().checkJwtToken(token, secret, issuer)) {
      filterChain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken("1", null, null);
    usernamePasswordAuthenticationToken.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    filterChain.doFilter(request, response);
  }
}
