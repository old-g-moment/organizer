package com.example.authservice.Models;

import lombok.Builder;

@Builder
public class BookModel {

  private Integer id;
  private String name;
  private Integer pageCount;
  private AuthorModel author;
}
