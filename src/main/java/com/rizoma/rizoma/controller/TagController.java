package com.rizoma.rizoma.controller;

import com.rizoma.rizoma.model.Tag;
import com.rizoma.rizoma.service.TagService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/tags")

public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<Object> createTag(@Valid @RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

}
