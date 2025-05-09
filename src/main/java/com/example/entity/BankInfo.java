package com.example.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BankInfo {
    private String bankAccount;
    private String ifscCode;
}
