package com.example.HiringAppSpringBoot.onboarding.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.HiringAppSpringBoot.onboarding.service.CandidateInfoService;
import com.example.HiringAppSpringBoot.onboarding.dto.PersonalInfoDTO;
import com.example.HiringAppSpringBoot.onboarding.dto.BankInfoDTO;
import com.example.HiringAppSpringBoot.onboarding.dto.EducationalInfoDTO;


@RestController
@RequestMapping("/api/candidates/{id}")
public class CandidateInfoController {

    private final CandidateInfoService infoService;

    public CandidateInfoController(CandidateInfoService infoService) {
        this.infoService = infoService;
    }
    @PutMapping("/personal-info")
    public ResponseEntity<?> updatePersonalInfo(@PathVariable Long id,
                                                @Valid @RequestBody PersonalInfoDTO dto) {
        infoService.updatePersonalInfo(id, dto);
        return ResponseEntity.ok("Personal info updated.");
    }

    @PutMapping("/bank-info")
    public ResponseEntity<?> updateBankInfo(@PathVariable Long id,
                                            @Valid @RequestBody BankInfoDTO dto) {
        infoService.updateBankInfo(id, dto);
        return ResponseEntity.ok("Bank info updated.");
    }

    @PutMapping("/educational-info")
    public ResponseEntity<?> updateEducationalInfo(@PathVariable Long id,
                                                   @Valid @RequestBody EducationalInfoDTO dto) {
        infoService.updateEducationalInfo(id, dto);
        return ResponseEntity.ok("Educational info updated.");
    }

}

