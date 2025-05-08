package com.example.HiringAppSpringBoot.onboarding.repository;

import com.example.HiringAppSpringBoot.onboarding.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.HiringAppSpringBoot.onboarding.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // Custom query methods if needed later
    List<Candidate> findByStatus(String status);
}

