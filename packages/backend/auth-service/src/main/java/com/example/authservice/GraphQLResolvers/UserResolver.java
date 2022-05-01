package com.example.authservice.GraphQLResolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.Models.Repos.UserRepo;
import com.example.authservice.Models.User;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLQueryResolver {

  private final UserRepo userRepo;

  public UserResolver(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public UserDTO userById(Long id) {
    final Optional<User> byId = userRepo.findById(id);
    System.out.println(byId);
    return byId.map(
        user -> new UserDTO(user.getId(), user.getEmail(), user.getToken(), user.getPassword())
    ).orElseThrow(() -> new IllegalArgumentException(""));
  }

  public UserDTO userByEmail(String email) {
    Optional<User> bygmail = userRepo.findByemail(email);
    return bygmail.map(
        user -> new UserDTO(user.getId(), user.getEmail(), user.getToken(), user.getPassword())
    ).orElseThrow(() -> new IllegalArgumentException(""));
  }
}
