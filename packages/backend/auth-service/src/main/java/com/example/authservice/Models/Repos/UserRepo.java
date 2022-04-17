package com.example.authservice.Models.Repos;

import com.example.authservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
