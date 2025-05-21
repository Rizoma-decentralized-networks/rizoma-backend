package com.rizoma.rizoma.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.rizoma.rizoma.repository.MarkRepository;
import com.rizoma.rizoma.repository.UserRepository;
import com.rizoma.rizoma.repository.TagRepository;
import com.rizoma.rizoma.repository.CategoryRepository;
import com.rizoma.rizoma.mapper.MarkMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

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
	void testExample(){
		
	}
}