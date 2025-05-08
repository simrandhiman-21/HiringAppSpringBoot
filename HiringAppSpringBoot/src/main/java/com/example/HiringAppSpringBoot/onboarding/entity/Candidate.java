package com.example.HiringAppSpringBoot.onboarding.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonalInfo personalInfo;

    @Embedded
    private BankInfo bankInfo;

    @Embedded
    private EducationInfo educationInfo;

    @Embedded
    private DocumentInfo documentInfo;

    @Enumerated(EnumType.STRING)
    private Status status;

    private UUID onboardingToken;
}
