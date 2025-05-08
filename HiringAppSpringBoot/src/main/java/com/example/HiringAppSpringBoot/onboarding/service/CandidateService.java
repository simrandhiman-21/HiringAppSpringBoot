package com.example.HiringAppSpringBoot.onboarding.service;

import com.example.HiringAppSpringBoot.onboarding.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateService{

    List<Candidate> getAllCandidates();

    Optional<Candidate> getCandidateById(Long id);

    long getCandidateCount();

    Candidate updateStatus(Long id, String status);

    Candidate updateOnboardStatus(Long id, boolean onboarded);

    Candidate saveCandidate(Candidate candidate);
}

