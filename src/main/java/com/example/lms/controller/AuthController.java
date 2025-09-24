package com.example.lms.controller;

import com.example.lms.dto.AuthRequest;
import com.example.lms.entity.User;
import com.example.lms.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "username taken"));
        }

        User u = new User();
        u.setUsername(username);
        u.setPassword(encoder.encode(password));
        u.setFullName(body.getOrDefault("fullName", ""));
        u.setEmail(body.getOrDefault("email", ""));
        u.setRoles(Collections.singleton("ROLE_USER"));

        userRepository.save(u);
        return ResponseEntity.ok(Collections.singletonMap("status", "ok"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Optional<User> userOpt = userRepository.findByUsernameOrEmail(req.getUsername(), req.getUsername());
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "invalid"));
        }

        User user = userOpt.get();
        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "invalid"));
        }

        // For simplicity return a dummy token (replace with JWT in production)
        String token = "dummytoken-for-" + user.getUsername();
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
