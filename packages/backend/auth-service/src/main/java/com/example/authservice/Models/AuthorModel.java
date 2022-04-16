package com.example.authservice.Models;

import lombok.Builder;

@Builder
public class AuthorModel {

  private Integer id;
  private String firstName;
  private String lastName;
}
