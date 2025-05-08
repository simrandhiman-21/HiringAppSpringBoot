package com.example.HiringAppSpringBoot.onboarding.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationInfo {

    private String degree;
    private String university;
    private int graduationYear;
}
