package com.rizoma.rizoma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {
    private Integer id_user;
    private String username;
    private String email;
    private String userImageUrl;
}

 // Getters y Setters
    // ...