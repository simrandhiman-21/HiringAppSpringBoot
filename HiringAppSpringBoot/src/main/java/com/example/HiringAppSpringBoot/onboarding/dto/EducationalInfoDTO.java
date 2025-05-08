package com.example.HiringAppSpringBoot.onboarding.dto;

import jakarta.validation.constraints.NotBlank;


public class EducationalInfoDTO {

    @NotBlank
    private String degree;

    @NotBlank
    private String university;

    private int passingYear;

    // Getters and Setters
    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public int getPassingYear() { return passingYear; }
    public void setPassingYear(int passingYear) { this.passingYear = passingYear; }
}
