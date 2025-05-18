package com.rizoma.dto;

public class UserDTO {
    private Long idUser;
    private String username;
    private String email;
    private String userImageUrl;

    // Constructor
    public UserDTO(Long idUser, String username, String email, String userImageUrl) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.userImageUrl = userImageUrl;
    }

}
 // Getters y Setters
    // ...