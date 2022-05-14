package com.example.authservice.Bootstrap;

import com.example.authservice.Models.Repos.UserRepo;
import com.example.authservice.Models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

  private final UserRepo userRepo;

  public Bootstrap(UserRepo userRepo) {
    this.userRepo = userRepo;
  }


  @Override
  public void run(String... args) {
    final User user = new User(1L, "teodr@gmail.com", "token", "dupa1234");

    userRepo.save(user);
  }
}
