package com.example.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobOfferMessage {
    private Long candidateId;
    private String fullName;
    private String email;
    private String jobTitle;
}

