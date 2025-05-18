package com.rizoma.rizoma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.rizoma.rizoma.service.UserService;
import com.rizoma.rizoma.model.User;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:5173")

public class UserController {
   private final UserService userService;

   public UserController(UserService userService) {
      this.userService = userService;
   }

   @PostMapping
   public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
      User createdUser = userService.createUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
   }

}