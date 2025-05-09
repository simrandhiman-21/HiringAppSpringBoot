package com.example.controller;

import com.example.entity.BankInfo;
import com.example.entity.EducationalInfo;
import com.example.entity.PersonalInfo;
import com.example.service.CandidateInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateInfoController {

    private final CandidateInfoService candidateInfoService;

    @PutMapping("/{id}/personal-info")
    public ResponseEntity<Void> updatePersonalInfo(@PathVariable Long id, @RequestBody PersonalInfo request) {
        candidateInfoService.updatePersonalInfo(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/bank-info")
    public ResponseEntity<Void> updateBankInfo(@PathVariable Long id, @RequestBody BankInfo request) {
        candidateInfoService.updateBankInfo(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/educational-info")
    public ResponseEntity<Void> updateEducationalInfo(@PathVariable Long id, @RequestBody EducationalInfo request) {
        candidateInfoService.updateEducationalInfo(id, request);
        return ResponseEntity.ok().build();
    }
}
