package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


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
}
