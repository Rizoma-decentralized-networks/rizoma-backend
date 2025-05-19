package com.rizoma.rizoma.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.rizoma.rizoma.model.Mark;
import com.rizoma.rizoma.repository.CategoryRepository;
import com.rizoma.rizoma.repository.MarkRepository;
import com.rizoma.rizoma.repository.TagRepository;
import com.rizoma.rizoma.repository.UserRepository;
import com.rizoma.rizoma.validation.ResourceNotFoundException;
import com.rizoma.rizoma.dto.MarkDTO;

@Service
public class MarkService {

    private final MarkRepository markRepository;


    @Autowired
    public MarkService(MarkRepository markRepository, UserRepository userRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.markRepository = markRepository;
    }

    public Mark findById(Integer id) {
        return markRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Mark not found"));
    }

    @Transactional
    public Mark create(MarkDTO markDTO) {
        Mark mark = new Mark();
        mark.setTitle(markDTO.getTitle());
        mark.setDescription(markDTO.getDescription());
        mark.setLocation(markDTO.getLocation());
        mark.setImageUrl(markDTO.getImageUrl());
        
         
        return markRepository.save(mark);
    }
}