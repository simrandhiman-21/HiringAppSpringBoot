package com.example.HiringAppSpringBoot.onboarding.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobOfferNotificationDTO {
    private Long candidateId;
    private String candidateEmail;
    private String candidateName;
    private String position;
}

