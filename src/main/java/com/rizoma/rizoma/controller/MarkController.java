package com.rizoma.rizoma.controller;

import org.springframework.web.bind.annotation.RestController;
import com.rizoma.rizoma.model.Mark;
import com.rizoma.rizoma.service.MarkService;
import jakarta.validation.Valid;
import java.util.List;
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

@RestController
@RequestMapping("/api/v1/marks")
@CrossOrigin(origins = "http://localhost:5173")
public class MarkController {

    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Object> createMark(@PathVariable Integer userId, @Valid @RequestBody Mark mark) {
        Mark createdMark = markService.createMark(mark, userId);
        if (createdMark == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not create mark. User or category not found.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMark);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMarkById(@PathVariable Integer id) {
        return this.markService.getMarkById(id);
    }

    @GetMapping
    public List<Mark> getAllMark() {
        return markService.getAllMarks();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMark(@PathVariable Integer id, @RequestBody Mark updatedMark) {
        return this.markService.updateMark(id, updatedMark);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        return this.markService.deleteById(id);
    }
}   