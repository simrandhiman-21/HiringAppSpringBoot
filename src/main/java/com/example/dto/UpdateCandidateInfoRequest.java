package com.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCandidateInfoRequest {

    // Personal Info
    private String address;

    // Bank Info
    private String bankAccount;
    private String ifscCode;

    // Educational Info
    private String collegeName;
    private String degree;
    private Integer graduationYear;
}
