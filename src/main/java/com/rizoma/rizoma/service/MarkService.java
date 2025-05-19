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
import com.rizoma.rizoma.model.Category;
import com.rizoma.rizoma.repository.CategoryRepository;
import com.rizoma.rizoma.model.Tag;
import com.rizoma.rizoma.repository.TagRepository;


@Service
public class MarkService {

    private final MarkRepository markRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    @Autowired
    public MarkService(MarkRepository markRepository, UserRepository userRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.markRepository = markRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional
    public Mark createMark(Mark mark, Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return null;
        }

        // Cargar explícitamente la categoría y el tag completos
        Optional<Category> categoryOptional = categoryRepository.findById(mark.getCategory().getCategoryId());
        Optional<Tag> tagOptional = tagRepository.findById(mark.getTag().getTagId());
        
        if (categoryOptional.isEmpty() || tagOptional.isEmpty()) {
            return null;
        }
        
        mark.setUser(userOptional.get());
        mark.setCategory(categoryOptional.get());
        mark.setTag(tagOptional.get());

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
    public ResponseEntity<Object> deleteById(Integer markId) {
        Optional<Mark> markOptional = markRepository.findById(markId);

        if (!markOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Mark mark = markOptional.get();

        markRepository.deleteById(mark.getMarkId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}