package com.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String status;
    private boolean isHired;
}
