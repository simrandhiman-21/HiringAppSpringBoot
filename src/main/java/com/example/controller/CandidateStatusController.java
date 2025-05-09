package com.example.controller;


import com.example.dto.OnboardStatusUpdateRequest;
import com.example.dto.StatusUpdateRequest;
import com.example.service.CandidateStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateStatusController {

    private final CandidateStatusService candidateStatusService;

        // // APPLIED, INTERVIEWED, OFFERED, ONBOARDED
    @PostMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        candidateStatusService.updateStatus(id, request.getStatus());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/onboard-status")
    public ResponseEntity<Void> updateOnboardStatus(@PathVariable Long id, @RequestBody OnboardStatusUpdateRequest request) {
        candidateStatusService.updateOnboardStatus(id, request.isOnboarded());
        return ResponseEntity.ok().build();
    }
}

