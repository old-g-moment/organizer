package com.example.authservice.GraphQLResolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.authservice.Models.AuthorModel;
import com.example.authservice.Models.BookModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookResolver implements GraphQLQueryResolver {

  public BookModel bookById(Integer id) {
    log.info(id.toString());
    return BookModel.builder()
        .id(id)
        .name("harry pothed")
        .pageCount(300)
        .author(AuthorModel.builder().build())
        .build();
  }
}
