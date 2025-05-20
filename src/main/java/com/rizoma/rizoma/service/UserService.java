package com.rizoma.rizoma.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rizoma.rizoma.model.User;

import com.rizoma.rizoma.repository.UserRepository;
import java.util.Optional; 
import com.rizoma.rizoma.exception.DuplicateUserException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicateUserException("Ya existe un usuario con el email: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    public ResponseEntity<Object> deleteUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(userOptional.get());
        return ResponseEntity.ok().build();
    }

}