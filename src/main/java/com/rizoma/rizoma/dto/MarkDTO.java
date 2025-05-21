package com.rizoma.rizoma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkDTO {
    private Integer idMark;
    private String title;
    private String description;
    private String location;
    private String imageUrl;
    private Integer category;
    private Integer tag;
   
}