package com.rizoma.dto;

public class MarkDTO {
    private Long idMark;
    private String title;
    private String description;
    private String location;
    private String imageUrl;
    private UserDTO user; // Referencia al DTO de User

    // Constructor
    public MarkDTO(Long idMark, String title, String description, String location, String imageUrl, UserDTO user) {
        this.idMark = idMark;
        this.title = title;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    // Getters y Setters
    // ...
}
