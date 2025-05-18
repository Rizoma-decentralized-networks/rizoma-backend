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

    @PostMapping("/user/{id_user}")
    public ResponseEntity<Object> createMark(@PathVariable Integer id_user, @Valid @RequestBody Mark mark) {
        if (mark.getCategory() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The category cannot be null.");
        } 
        Mark createdMark = markService.createMark(mark, id_user);
        if (createdMark == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not create mark. User or category not found.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMark);
    }

    @GetMapping("/{id_mark}")
    public ResponseEntity<Object> getMarkById(@PathVariable Integer id_mark ) {
        return this.markService.getMarkById(id_mark);
    }

    @GetMapping
    public List<Mark> getAllMark() {
        return markService.getAllMarks();
    }

    @PutMapping("/{id_mark}")
    public ResponseEntity<Object> updateMark(@PathVariable Integer id_mark, @RequestBody Mark updatedMark) {
        return this.markService.updateMark(id_mark, updatedMark);
    }

    @DeleteMapping("/{id_mark}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id_mark) {
        return this.markService.deleteById(id_mark);
    }
}   