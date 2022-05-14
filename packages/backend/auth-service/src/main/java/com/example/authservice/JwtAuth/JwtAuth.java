package com.example.authservice.JwtAuth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class JwtAuth extends WebSecurityConfigurerAdapter {

  private final RequestFilter requestFilter;

  public JwtAuth(RequestFilter requestFilter) {
    this.requestFilter = requestFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http = http.cors().and().csrf().disable();
    http = http.exceptionHandling().authenticationEntryPoint((req, res, ex) -> {
      res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    }).and();
    http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

    http.authorizeRequests().anyRequest().authenticated();
    http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.addAllowedOrigin("/auth/*");
    configuration.addAllowedMethod("POST GET DELETE PUT");
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
    return new CorsFilter(urlBasedCorsConfigurationSource);
  }
}
