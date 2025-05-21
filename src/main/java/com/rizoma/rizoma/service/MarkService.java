package com.rizoma.rizoma.service;

import java.util.Optional;
import java.util.List;
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
import com.rizoma.rizoma.dto.MarkDTO;
import com.rizoma.rizoma.exception.DuplicateDataExcepction;
import com.rizoma.rizoma.mapper.MarkMapper;

@Service
public class MarkService {

    private final MarkRepository markRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final MarkMapper markMapper;
    

    @Autowired
    public MarkService(MarkRepository markRepository, 
                    UserRepository userRepository, 
                    CategoryRepository categoryRepository, 
                    TagRepository tagRepository,
                    MarkMapper markMapper) {
        this.markRepository = markRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.markMapper = markMapper;
    }

    @Transactional
    public Mark createMarkFromDTO(MarkDTO dto, Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        if (dto.getCategory() == null || dto.getTag() == null) {
            throw new IllegalArgumentException("Category and Tag IDs must not be null");
        }

        Optional<Category> categoryOptional = categoryRepository.findById(dto.getCategory());
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }

        Optional<Tag> tagOptional = tagRepository.findById(dto.getTag());
        if (tagOptional.isEmpty()) {
            throw new IllegalArgumentException("Tag not found");
        }

        Optional<Mark> existingMark = markRepository.findByTitleAndLocation(
            dto.getTitle(), dto.getLocation()
        );

        if (existingMark.isPresent()) {
            throw new DuplicateDataExcepction("A marker with this title and location already exists");
        }

        Mark mark = markMapper.toEntity(dto, categoryOptional.get(), tagOptional.get());
        mark.setUser(userOptional.get());
        return markRepository.save(mark);
    }


    public List<Mark> getAllMarks() {
        return this.markRepository.findAll();
    }

    public Mark findMarkById(Integer id) {
        return this.markRepository.findById(id).orElse(null);
    }

    @Transactional
    public Mark updateMark(Integer id, MarkDTO dto) {
        Optional<Mark> markOptional = markRepository.findById(id);
        if (markOptional.isEmpty()) return null;

        Mark existingMark = markOptional.get();

        existingMark.setTitle(dto.getTitle());
        existingMark.setDescription(dto.getDescription());
        existingMark.setLocation(dto.getLocation());
        existingMark.setImageUrl(dto.getImageUrl());
        
        if (dto.getCategory() != null) {
            categoryRepository.findById(dto.getCategory())
                .ifPresent(existingMark::setCategory);
        }
        if (dto.getTag() != null) {
            tagRepository.findById(dto.getTag())
                .ifPresent(existingMark::setTag);
        }

        return markRepository.save(existingMark);
    }


    public ResponseEntity<Object> getMarkById(Integer id) {
        Optional<Mark> markOptional = markRepository.findById(id);
        if (!markOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Mark mark = markOptional.get();
        return ResponseEntity.ok(mark);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteById(Integer markId) {
        Optional<Mark> markOptional = markRepository.findById(markId);
        if (markOptional.isEmpty()) return false;

        markRepository.deleteById(markId);
        return true;
    }
}