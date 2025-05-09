package com.example.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EducationalInfo {
    private String collegeName;
    private String degree;
    private Integer graduationYear;
}
