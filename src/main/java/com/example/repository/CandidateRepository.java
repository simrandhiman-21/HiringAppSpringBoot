package com.example.repository;

import com.example.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByIsHiredTrue();
    long countByIsHiredTrue();
}
