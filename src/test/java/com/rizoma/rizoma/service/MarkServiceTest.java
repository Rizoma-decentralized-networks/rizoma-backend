package com.rizoma.rizoma.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.rizoma.rizoma.repository.MarkRepository;
import com.rizoma.rizoma.repository.UserRepository;
import com.rizoma.rizoma.repository.TagRepository;
import com.rizoma.rizoma.repository.CategoryRepository;
import com.rizoma.rizoma.dto.MarkDTO;
import com.rizoma.rizoma.mapper.MarkMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Optional;
import com.rizoma.rizoma.model.Mark;
import com.rizoma.rizoma.model.User;
import com.rizoma.rizoma.model.Tag;
import com.rizoma.rizoma.model.Category;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MarkServiceTest {
	@Mock
	private MarkRepository markRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private TagRepository tagRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private MarkMapper markMapper;

	@InjectMocks
	private MarkService markService;

	@BeforeEach
	void setUp() {

	}

	@Test
	void testCreateMark() {
		Integer userId = 1;
		
		MarkDTO markDTO = new MarkDTO();
		markDTO.setTitle("Test Mark");
		markDTO.setDescription("This is a test mark");
		markDTO.setLocation("Test Location");
		markDTO.setImageUrl("http://example.com/image.jpg");
		markDTO.setCategory(1);
		markDTO.setTag(2);
		
		User user = new User();
		user.setUserId(userId);
		
		Category category = new Category();
		category.setCategoryId(1);
		
		Tag tag = new Tag();
		tag.setTagId(2);
		
		Mark mappedMark = new Mark();
		mappedMark.setTitle(markDTO.getTitle());
		mappedMark.setDescription(markDTO.getDescription());
		mappedMark.setLocation(markDTO.getLocation());
		mappedMark.setImageUrl(markDTO.getImageUrl());
		
		Mark savedMark = new Mark();
		savedMark.setMarkId(1);
		savedMark.setTitle(markDTO.getTitle());
		savedMark.setUser(user);
		
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(categoryRepository.findById(markDTO.getCategory())).thenReturn(Optional.of(category));
		when(tagRepository.findById(markDTO.getTag())).thenReturn(Optional.of(tag));
		when(markRepository.findByTitleAndLocation(markDTO.getTitle(), markDTO.getLocation()))
			.thenReturn(Optional.empty());
		
		when(markMapper.toEntity(eq(markDTO), eq(category), eq(tag))).thenReturn(mappedMark);
		
		when(markRepository.save(any(Mark.class))).thenReturn(savedMark);
		
		Mark result = markService.createMarkFromDTO(markDTO, userId);
		
		assertNotNull(result);
		assertEquals("Test Mark", result.getTitle());
		
		verify(userRepository).findById(userId);
		verify(categoryRepository).findById(markDTO.getCategory());
		verify(tagRepository).findById(markDTO.getTag());
		verify(markMapper).toEntity(eq(markDTO), eq(category), eq(tag));
		verify(markRepository).save(any(Mark.class));
	}
}
