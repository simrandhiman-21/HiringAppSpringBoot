package com.example.service.impl;

import com.example.entity.Candidate;
import com.example.entity.CandidateStatus;
import com.example.exception.CandidateNotFoundException;
import com.example.exception.InvalidStatusException;
import com.example.repository.CandidateRepository;
import com.example.service.CandidateStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateStatusServiceImpl implements CandidateStatusService {

    private final CandidateRepository candidateRepository;

    @Override
    public void updateStatus(Long candidateId, String status) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + candidateId + " not found"));

        try {
            CandidateStatus newStatus = CandidateStatus.valueOf(status.toUpperCase());
            candidate.setStatus(newStatus);
            candidateRepository.save(candidate);
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException("Invalid status: " + status);
        }
    }

    @Override
    public void updateOnboardStatus(Long candidateId, boolean onboarded) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + candidateId + " not found"));

        candidate.setStatus(onboarded ? CandidateStatus.ONBOARDED : CandidateStatus.OFFERED);
        candidate.setHired(onboarded);
        candidateRepository.save(candidate);
    }
}

