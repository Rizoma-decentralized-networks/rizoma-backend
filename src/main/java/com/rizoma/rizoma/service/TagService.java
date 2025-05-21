package com.rizoma.rizoma.service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rizoma.rizoma.model.Tag;
import com.rizoma.rizoma.repository.TagRepository;
import java.util.List;
import com.rizoma.rizoma.exception.DuplicateDataExcepction;
import com.rizoma.rizoma.exception.ResourceNotFoundException;

@Service

public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public ResponseEntity<Object> createTag(Tag tag) {  

        if(tagRepository.findByTag(tag.getTag()) != null){
           throw new DuplicateDataExcepction("[ERROR]: Duplicate data");
        }

        return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.CREATED);
    }

    public List<Tag> getAllTags() {
        return this.tagRepository.findAll();
    }

    public Tag getTagById(Integer id) {
        return this.tagRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
    }
}
