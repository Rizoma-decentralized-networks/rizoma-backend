package com.rizoma.rizoma.controller;

import com.rizoma.rizoma.model.Tag;
import com.rizoma.rizoma.service.TagService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("api/v1/tags")
@CrossOrigin(origins = "http://localhost:5173")

public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<Object> createTag(@Valid @RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Integer id) {
        Tag tag = tagService.getTagById(id);
        return ResponseEntity.ok(tag);
    }

}
