package com.rizoma.rizoma.repository;

import com.rizoma.rizoma.model.Mark;
import com.rizoma.rizoma.model.Category;
import com.rizoma.rizoma.model.User;
import com.rizoma.rizoma.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {

    List<Mark> findByTitle(String title);

    Page<Mark> findByCategory(Category category, Pageable pageable);    

    Page<Mark> findByTag(Tag tag, Pageable pageable);

    List<Mark> findByUser(User user, Pageable pageable);
    
}