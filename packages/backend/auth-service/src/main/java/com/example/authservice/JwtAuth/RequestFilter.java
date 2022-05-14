package com.example.authservice.JwtAuth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authservice.Models.Repos.UserRepo;
import com.example.authservice.Models.User;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private final UserRepo userRepo;
  private final Logger logger = LoggerFactory.getLogger(RequestFilter.class);
  public RequestFilter(@NotNull UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain) throws ServletException, IOException {
    if (request.getHeader(HttpHeaders.AUTHORIZATION) == null || !request.getHeader(
        HttpHeaders.AUTHORIZATION).startsWith("OGM-Auth-Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];

    try {
      DecodedJWT decodedJWT = JwtValidatorImpl.getInstance(secret, issuer).checkJwtToken(token);
      String email = JwtDataGetterImpl.getJwtDataGetter().getEmailFromJWT(decodedJWT);
      final Optional<User> userByEmail = userRepo.findByemail(email);
      logger.info(userByEmail.toString());
      System.out.println("DUPA");
      userByEmail.map((user) -> {
        logger.info(user.getEmail());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(user.getId(), null, null);
        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        return Optional.empty();
      }).orElseThrow(() -> new IllegalAccessException("User with passed token doesn't exist"));
      filterChain.doFilter(request, response);
    } catch (final JWTVerificationException | IllegalArgumentException | IllegalAccessException error) {
      System.out.println("DUPA1");
      logger.error(String.format("User with this token: %s \ncannot be authorized", token));
      logger.error(String.format("Error message: %s", error.getMessage()));
      error.printStackTrace();
      filterChain.doFilter(request, response);
    }
  }
}
