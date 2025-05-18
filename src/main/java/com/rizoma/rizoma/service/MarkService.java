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
import com.rizoma.rizoma.model.Tag;
import com.rizoma.rizoma.model.Category;
import com.rizoma.rizoma.repository.CategoryRepository;
import com.rizoma.rizoma.repository.MarkRepository;
import com.rizoma.rizoma.repository.TagRepository;
import com.rizoma.rizoma.repository.UserRepository;

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
    public Mark createMark(Mark mark, Integer id_user) {
    Optional<User> userOptional = userRepository.findById(id_user);
    if (userOptional.isEmpty()) {
        return null;
    }

    Integer categoryId = mark.getCategory() != null ? mark.getCategory().getId_category() : null;
    Integer tagId = mark.getTag() != null ? mark.getTag().getId_tag() : null;

    if (categoryId == null || tagId == null) {
        return null;
    }

    Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
    Optional<Tag> tagOptional = tagRepository.findById(tagId);

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

    public ResponseEntity<Object> getMarkById(Integer id_mark) {
        Optional<Mark> markOptional = markRepository.findById(id_mark);
        if (!markOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Mark mark = markOptional.get();
        return ResponseEntity.ok(mark);
    }

    public ResponseEntity<Object> updateMark(Integer id_mark, Mark updateMark) {
        Optional<Mark> markOptional = markRepository.findById(id_mark);

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
    public ResponseEntity<Object> deleteById(Integer id_mark) {
        Optional<Mark> markOptional = markRepository.findById(id_mark);

        if (!markOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Mark mark = markOptional.get();

        markRepository.deleteById(mark.getId_mark());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}