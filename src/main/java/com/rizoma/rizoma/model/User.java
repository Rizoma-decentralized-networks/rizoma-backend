package com.rizoma.rizoma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "userId")

public class User {
    @Id
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Integer userId;

    @Column
    @NotBlank(message = "[ERROR!] Name camp is required and cannot be empty")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "[ERROR!] No está permitido el uso de caracteres especiales")
    @Size(max = 20, message = "[ERROR!] Maximum of 20 characters allowed in this field")
    private String username;

    @Column
    @NotBlank(message = "[ERROR!] Password camp is required and cannot be empty")
    @Size(max = 20, message = "[ERROR!] Maximum of 20 characters allowed in this field")
    private String password;

    @Column
    @Email(message = "[ERROR!] Please enter a valid email format")
    @NotBlank(message = "[ERROR!] Email camp is required and cannot be empty")
    @Size(max = 50, message = "[ERROR!] Maximum of 50 characters allowed in this field")
    private String email;

    @Column
    @Pattern(regexp = "^(http|https)://.*$", message = "The image URL must be a valid HTTP or HTTPS URL")
    private String userImageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> marks;
}
