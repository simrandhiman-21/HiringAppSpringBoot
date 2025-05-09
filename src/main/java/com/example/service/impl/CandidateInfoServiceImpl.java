package com.example.service.impl;

import com.example.dto.UpdateCandidateInfoRequest;
import com.example.entity.*;
import com.example.exception.CandidateNotFoundException;
import com.example.repository.CandidateRepository;
import com.example.service.CandidateInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.entity.PersonalInfo;
import com.example.entity.BankInfo;
import com.example.entity.EducationalInfo;

@Service
@RequiredArgsConstructor
public class CandidateInfoServiceImpl implements CandidateInfoService {

    private final CandidateRepository candidateRepository;

    @Override
    public void updatePersonalInfo(Long candidateId, PersonalInfo request) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + candidateId + " not found"));

        PersonalInfo info = candidate.getPersonalInfo() != null ? candidate.getPersonalInfo() : new PersonalInfo();
        info.setAddress(request.getAddress());
        candidate.setPersonalInfo(info);

        candidateRepository.save(candidate);
    }

    @Override
    public void updateBankInfo(Long candidateId, BankInfo request) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + candidateId + " not found"));

        BankInfo info = candidate.getBankInfo() != null ? candidate.getBankInfo() : new BankInfo();
        info.setBankAccount(request.getBankAccount());
        info.setIfscCode(request.getIfscCode());
        candidate.setBankInfo(info);

        candidateRepository.save(candidate);
    }

    @Override
    public void updateEducationalInfo(Long candidateId, EducationalInfo request) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + candidateId + " not found"));

        EducationalInfo info = candidate.getEducationalInfo() != null ? candidate.getEducationalInfo() : new EducationalInfo();
        info.setCollegeName(request.getCollegeName());
        info.setDegree(request.getDegree());
        info.setGraduationYear(request.getGraduationYear());
        candidate.setEducationalInfo(info);

        candidateRepository.save(candidate);
    }

    @Override
    public void updateOnboardStatus(Long candidateId, boolean onboarded) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + candidateId + " not found"));

        // Validate personal info
        if (onboarded) {
            if (candidate.getPersonalInfo() == null || candidate.getPersonalInfo().getAddress() == null) {
                throw new IllegalStateException("Cannot onboard without personal info.");
            }

            if (candidate.getDocument() == null || !candidate.getDocument().isVerified()) {
                throw new IllegalStateException("Cannot onboard without verified document.");
            }

            candidate.setStatus(CandidateStatus.ONBOARDED);
            candidate.setHired(true);
        } else {
            candidate.setStatus(CandidateStatus.OFFERED);
            candidate.setHired(false);
        }

        candidateRepository.save(candidate);
    }

}
