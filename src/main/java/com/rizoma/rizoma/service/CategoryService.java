package com.rizoma.rizoma.service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rizoma.rizoma.model.Category;
import com.rizoma.rizoma.repository.CategoryRepository;
import java.util.List;
import com.rizoma.rizoma.exception.ResourceNotFoundException;

@Service

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Object> createCategory(Category category) {

        if(categoryRepository.findByCategory(category.getCategory()) != null){
            throw new ResourceNotFoundException("Category", "category", category.getCategory());
        }

        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return this.categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

}