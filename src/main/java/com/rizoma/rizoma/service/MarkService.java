package com.rizoma.rizoma.service;

import java.util.Optional;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.rizoma.rizoma.model.Mark;
import com.rizoma.rizoma.model.User;
import com.rizoma.rizoma.repository.MarkRepository;
import com.rizoma.rizoma.repository.UserRepository;

@Service
public class MarkService {

    private final MarkRepository markRepository;
    private final UserRepository userRepository;

    @Autowired
    public MarkService(MarkRepository markRepository, UserRepository userRepository) {
        this.markRepository = markRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Mark createMark(Mark mark, Integer userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return null;
        }

        mark.setUser(userOptional.get());

        return markRepository.save(mark);
    }

    public List<Mark> getAllMarks() {
        return this.markRepository.findAll();
    }

    public ResponseEntity<Object> getMarkById(Integer id) {
        Optional<Mark> markOptional = markRepository.findById(id);
        if (!markOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Mark mark = markOptional.get();
        return ResponseEntity.ok(mark);
    }

    public ResponseEntity<Object> updateMark(Integer id, Mark updateMark) {
        Optional<Mark> markOptional = markRepository.findById(id);

        if (!markOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Mark existingMark = markOptional.get();

        existingMark.setTitle(updateMark.getTitle());
        existingMark.setDescription(updateMark.getDescription());
        markRepository.save(existingMark);
            return ResponseEntity.ok(existingMark);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> deleteById(Integer id) {
        Optional<Mark> markOptional = markRepository.findById(id);

        if (!markOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Mark mark = markOptional.get();

        markRepository.deleteById(mark.getIdMark());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}