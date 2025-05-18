package com.rizoma.rizoma.service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rizoma.rizoma.model.Tag;
import com.rizoma.rizoma.repository.TagRepository;

@Service

public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public ResponseEntity<Object> createTag(Tag tag) {

        if(tagRepository.findByTag(tag.getTag()) != null){
        //  throw new TagAlreadyExistsException("[ERROR]: Ya existe una etiqueta con ese nombre");
        }

        return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.CREATED);
    }

}