package com.example.HiringAppSpringBoot.onboarding.service;

import com.example.HiringAppSpringBoot.onboarding.repository.CandidateRepository;
import com.example.HiringAppSpringBoot.onboarding.entity.Candidate;
import com.example.HiringAppSpringBoot.onboarding.entity.Status;
import com.example.HiringAppSpringBoot.onboarding.exception.CandidateNotFoundException;
import com.example.HiringAppSpringBoot.onboarding.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {


    @Autowired
    private final CandidateRepository candidateRepository;

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public long getCandidateCount() {
        return candidateRepository.count();
    }

    @Override
    public Candidate updateStatus(Long id, String status) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        candidate.setStatus(Status.valueOf(status.toUpperCase()));
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate updateOnboardStatus(Long id, boolean onboarded) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        if (onboarded) {
            candidate.setStatus(Status.ONBOARDED);
        }
        return candidateRepository.save(candidate);
    }
    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

}
