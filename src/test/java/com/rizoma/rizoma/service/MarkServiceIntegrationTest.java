package com.rizoma.rizoma.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.rizoma.rizoma.model.Mark;
import com.rizoma.rizoma.dto.MarkDTO;
import com.rizoma.rizoma.repository.MarkRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MarkServiceIntegrationTest {

    @Autowired
    private MarkService markService;
    
    @Autowired
    private MarkRepository markRepository;
    
    
    @Test
    void testDataLoaded() {
        assertNotNull(markService);
        assertTrue(markService.getAllMarks().size() > 0);
    }
    
   
    @Test
    void testCreateNewMark() {
        MarkDTO markDTO = new MarkDTO();
        markDTO.setTitle("New Test Mark");
        markDTO.setDescription("This is a new test mark");
        markDTO.setLocation("New Test Location");
        markDTO.setImageUrl("http://example.com/new-image.jpg");
        markDTO.setCategory(1);
        markDTO.setTag(2);
        
        Mark createdMark = markService.createMarkFromDTO(markDTO, 1);
        
        assertNotNull(createdMark);
        assertNotNull(createdMark.getMarkId());
        assertEquals("New Test Mark", createdMark.getTitle());
        assertEquals("New Test Location", createdMark.getLocation());
        
        assertTrue(markRepository.findById(createdMark.getMarkId()).isPresent());
    }
}
