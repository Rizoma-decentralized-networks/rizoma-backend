package com.rizoma.rizoma.mapper;

import com.rizoma.rizoma.dto.MarkDTO;
import com.rizoma.rizoma.dto.CategoryDTO;
import com.rizoma.rizoma.dto.TagDTO;
import com.rizoma.rizoma.model.Mark;
import com.rizoma.rizoma.model.Category;
import com.rizoma.rizoma.model.Tag;
import org.springframework.stereotype.Component;


@Component
public class MarkMapper {


    public MarkDTO toDTO(Mark mark) {
        if (mark == null) return null;

        CategoryDTO categoryDTO = null;
        if (mark.getCategory() != null) {
            categoryDTO = new CategoryDTO(
                mark.getCategory().getCategoryId(),
                mark.getCategory().getCategory()
            );
        }

        TagDTO tagDTO = null;
        if (mark.getTag() != null) {
            tagDTO = new TagDTO(
                mark.getTag().getTagId(),
                mark.getTag().getTag()
            );
        }

        return new MarkDTO(
            mark.getMarkId(),
            mark.getTitle(),
            mark.getDescription(),
            mark.getLocation(),
            mark.getImageUrl(),
            categoryDTO,
            tagDTO
        );
    }

    public Mark toEntity(MarkDTO dto, Category category, Tag tag) {
        if (dto == null) return null;

        Mark mark = new Mark();
        mark.setMarkId(dto.getIdMark());
        mark.setTitle(dto.getTitle());
        mark.setDescription(dto.getDescription());
        mark.setLocation(dto.getLocation());
        mark.setImageUrl(dto.getImageUrl());
        mark.setCategory(category);
        mark.setTag(tag);

        return mark;
    }
}
