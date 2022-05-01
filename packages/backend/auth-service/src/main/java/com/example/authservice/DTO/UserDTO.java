package com.example.authservice.DTO;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public final class UserDTO {

  @NotNull
  private final Long id;
  @NotNull
  private final String email;
  @NotNull
  private final String token;
  @NotNull
  private final String password;
}
