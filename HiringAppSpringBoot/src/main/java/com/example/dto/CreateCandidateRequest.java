package com.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCandidateRequest {
    private String fullName;
    private String email;
    private String phone;
}
