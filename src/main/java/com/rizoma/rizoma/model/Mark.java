package com.rizoma.rizoma.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "marks")
public class Mark {

    @Id
    @SequenceGenerator(name = "mark_id_sequence", sequenceName = "mark_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_id_sequence")
    private Integer id;

    @Column
    @NotBlank(message = "[ERROR!] The title field cannot be empty")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "[ERROR!] Special characters are not allowed")
    @Size(max = 100, message = "[ERROR!] Maximum of 100 characters allowed in this field")
    private String title;

    @Column
    @NotBlank(message = "[ERROR!] The content field cannot be empty")
    @Size(min = 50, max = 2000, message = "[ERROR!] Minimum of 50 and maximum of 2000 characters allowed in this field")
    @Pattern(regexp = "^[^\\/*<>|]+$", message = "[ERROR!] Special characters are not allowed")
    private String description;

    @Column
    @NotBlank(message = "[ERROR!] The location field cannot be empty")
    @Pattern(regexp = "^[^\\/*<>|]+$", message = "[ERROR!] Special characters are not allowed")
    private String location;

    @Column
    @Pattern(regexp = "^(http|https)://.*$", message = "The image URL must be a valid HTTP or HTTPS URL")
    private String imageUrl;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
