package com.rizoma.rizoma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkDTO {
    private Integer id_mark;
    private String title;
    private String description;
    private String location;
    private String imageUrl;
    private UserSummaryDTO user;
    private CategoryDTO category;
    private TagDTO tag;
}
