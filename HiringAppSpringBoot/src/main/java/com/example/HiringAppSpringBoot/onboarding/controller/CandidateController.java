package com.example.HiringAppSpringBoot.onboarding.controller;

import com.example.HiringAppSpringBoot.onboarding.service.CandidateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.HiringAppSpringBoot.onboarding.entity.Candidate;


import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateServiceImpl candidateService;

    // GET /api/candidates/hired
    @GetMapping("/hired")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    // GET /api/candidates/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Candidate not found")); // handled globally
    }

    // GET /api/candidates/count
    @GetMapping("/count")
    public ResponseEntity<Long> getCandidateCount() {
        return ResponseEntity.ok(candidateService.getCandidateCount());
    }

    // POST /api/candidates/{id}/status
    @PostMapping("/{id}/status")
    public ResponseEntity<Candidate> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(candidateService.updateStatus(id, status));
    }

    // PUT /api/candidates/{id}/onboard-status
    @PutMapping("/{id}/onboard-status")
    public ResponseEntity<Candidate> updateOnboardStatus(@PathVariable Long id, @RequestParam boolean onboarded) {
        return ResponseEntity.ok(candidateService.updateOnboardStatus(id, onboarded));
    }

    // POST /api/candidates
    @PostMapping
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate) {
        return ResponseEntity.ok(candidateService.saveCandidate(candidate));
    }



}
