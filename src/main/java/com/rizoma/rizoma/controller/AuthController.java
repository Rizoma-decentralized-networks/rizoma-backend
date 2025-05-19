package com.rizoma.rizoma.controller;

import com.rizoma.rizoma.dto.*;
import com.rizoma.rizoma.model.User;
import com.rizoma.rizoma.repository.UserRepository;
import com.rizoma.rizoma.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElse(null);
        if (user == null || !passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        // Temporal: devolvemos un token "falso" como placeholder
        String fakeToken = "fake-jwt-token"; // luego reemplazaremos con uno real

        return ResponseEntity.ok(new AuthResponse(fakeToken, UserMapper.toDTO(user)));
    }
}
