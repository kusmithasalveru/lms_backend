package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lms.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);
}
