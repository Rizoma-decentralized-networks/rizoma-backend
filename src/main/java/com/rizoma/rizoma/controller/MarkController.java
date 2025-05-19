package com.rizoma.rizoma.controller;

import org.springframework.web.bind.annotation.RestController;
import com.rizoma.rizoma.model.Mark;
import com.rizoma.rizoma.service.MarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.rizoma.rizoma.dto.MarkDTO;
import com.rizoma.rizoma.mapper.MarkMapper;

@RestController
@RequestMapping("/api/v1/marks")
@CrossOrigin(origins = "http://localhost:5173")
public class MarkController {

    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }
 

    @GetMapping("/{id}")
    public ResponseEntity<MarkDTO> getMark(@PathVariable Long id) {
        Mark mark = markService.findById(id.intValue());
        return ResponseEntity.ok(MarkMapper.toDTO(mark));
    }

    @PostMapping
    public ResponseEntity<MarkDTO> createMark(@RequestBody MarkDTO markDTO) {
        Mark mark = markService.create(markDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(MarkMapper.toDTO(mark));
    }
}   