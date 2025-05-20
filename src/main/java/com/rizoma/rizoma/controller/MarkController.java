package com.rizoma.rizoma.controller;

import org.springframework.web.bind.annotation.RestController;
import com.rizoma.rizoma.model.Mark;
import com.rizoma.rizoma.service.MarkService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.rizoma.rizoma.mapper.MarkMapper;
import com.rizoma.rizoma.dto.MarkDTO;


@RestController
@RequestMapping("/api/v1/marks")
@CrossOrigin(origins = "http://localhost:5173")
public class MarkController {

    private final MarkService markService;
    private final MarkMapper markMapper;

    @Autowired
    public MarkController(MarkService markService, MarkMapper markMapper) {
        this.markService = markService;
        this.markMapper = markMapper;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Object> createMark(@PathVariable Integer userId, @Valid @RequestBody MarkDTO markDTO) {
        Mark createdMark = markService.createMarkFromDTO(markDTO, userId);
        if (createdMark == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not create mark. User or category not found.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(markMapper.toDTO(createdMark));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMarkById(@PathVariable Integer id) {
        Mark mark = markService.findMarkById(id);
        if (mark == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mark not found");
        }
        return ResponseEntity.ok(markMapper.toDTO(mark));
    }

    @GetMapping
    public List<MarkDTO> getAllMark() {
        List<Mark> marks = markService.getAllMarks();
        return marks.stream()
            .map(markMapper::toDTO)
            .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMark(@PathVariable Integer id, @RequestBody MarkDTO updatedMarkDTO) {
        Mark updatedMark = markService.updateMark(id, updatedMarkDTO);
        if (updatedMark == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mark not found");
        }
        return ResponseEntity.ok(markMapper.toDTO(updatedMark));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        boolean deleted = this.markService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mark not found");
        }
        return ResponseEntity.noContent().build();
    }
}   