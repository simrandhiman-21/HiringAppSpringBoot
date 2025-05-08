package com.example.HiringAppSpringBoot.onboarding.exception;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(Long id) {
        super("Candidate with ID " + id + " does not exist");
    }
}

