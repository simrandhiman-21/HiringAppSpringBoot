package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import com.example.entity.PersonalInfo;
import com.example.entity.BankInfo;
import com.example.entity.EducationalInfo;




@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private CandidateStatus status;


    private boolean isHired;

    @Embedded
    private PersonalInfo personalInfo;

    @Embedded
    private BankInfo bankInfo;

    @Embedded
    private EducationalInfo educationalInfo;

    // Optional: add mapping back if needed
    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Document document;

}
