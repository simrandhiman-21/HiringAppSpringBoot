package com.example.controller;


import com.example.dto.CandidateDTO;
import com.example.dto.CreateCandidateRequest;
import com.example.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CreateCandidateRequest request) {
        CandidateDTO created = candidateService.createCandidate(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/hired")
    public ResponseEntity<List<CandidateDTO>> getHiredCandidates() {
        return ResponseEntity.ok(candidateService.getAllHiredCandidates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDTO> getCandidate(@PathVariable Long id) {
        return ResponseEntity.ok(candidateService.getCandidateById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getHiredCandidateCount() {
        return ResponseEntity.ok(candidateService.getHiredCandidateCount());
    }


}

