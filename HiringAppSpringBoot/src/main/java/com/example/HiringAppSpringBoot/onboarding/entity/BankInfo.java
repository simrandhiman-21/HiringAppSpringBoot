package com.example.HiringAppSpringBoot.onboarding.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankInfo {

    private String bankName;
    private String bankAccountNumber;
    private String ifscCode;
}



