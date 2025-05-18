package com.rizoma.rizoma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tags")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id_tag")
public class Tag {

    @Id
    @SequenceGenerator(name = "tag_id_sequence", sequenceName = "tag_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_sequence")
    private Integer id_tag;

    @Column
    @NotBlank(message = "[ERROR!] The tag field cannot be empty")
    @Size(min = 3, max = 20, message = "[ERROR!] Minimum of 3 and maximum of 20 characters allowed in this field")
    @Pattern(regexp = "^[^\\/*<>|]+$", message = "[ERROR!] Special characters are not allowed")
    private String tag;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> marks;
}
