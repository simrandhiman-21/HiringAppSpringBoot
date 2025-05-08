package com.example.HiringAppSpringBoot.onboarding.entity;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentInfo {
    private boolean documentVerified;
    private String resumePath;
    private String offerLetterPath;
}


