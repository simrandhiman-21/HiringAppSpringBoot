package com.example.service;

public interface CandidateStatusService {
    void updateStatus(Long candidateId, String status);
    void updateOnboardStatus(Long candidateId, boolean onboarded);
}

