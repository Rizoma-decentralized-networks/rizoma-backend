package com.rizoma.rizoma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token; // por ahora, puedes devolver un string fijo como prueba
    private UserSummaryDTO user;
}
