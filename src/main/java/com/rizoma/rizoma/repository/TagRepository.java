package com.rizoma.rizoma.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rizoma.rizoma.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findByTag(String tag);

}