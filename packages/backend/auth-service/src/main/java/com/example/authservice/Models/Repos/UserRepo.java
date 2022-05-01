package com.example.authservice.Models.Repos;

import com.example.authservice.Models.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

  Optional<User> findByemail(String gmail);
}
