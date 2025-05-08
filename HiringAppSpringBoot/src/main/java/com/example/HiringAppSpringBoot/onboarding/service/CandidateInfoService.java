package com.example.HiringAppSpringBoot.onboarding.service;

import com.example.HiringAppSpringBoot.onboarding.dto.PersonalInfoDTO;
import com.example.HiringAppSpringBoot.onboarding.dto.EducationalInfoDTO;
import com.example.HiringAppSpringBoot.onboarding.dto.BankInfoDTO;
import com.example.HiringAppSpringBoot.onboarding.entity.Candidate;
import com.example.HiringAppSpringBoot.onboarding.entity.PersonalInfo;
import com.example.HiringAppSpringBoot.onboarding.entity.BankInfo;
import com.example.HiringAppSpringBoot.onboarding.entity.EducationInfo;
import com.example.HiringAppSpringBoot.onboarding.exception.CandidateNotFoundException;
import com.example.HiringAppSpringBoot.onboarding.repository.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateInfoService {

    private final CandidateRepository repository;

    public CandidateInfoService(CandidateRepository repository) {
        this.repository = repository;
    }

    public void updatePersonalInfo(Long id, PersonalInfoDTO dto) {
        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        String[] names = dto.getFullName().trim().split(" ", 2);

        PersonalInfo personal = new PersonalInfo();
        personal.setFirstName(names[0]);
        personal.setLastName(names.length > 1 ? names[1] : "");
        personal.setPhoneNumber(dto.getPhone());
        personal.setDateOfBirth(dto.getDateOfBirth());
        personal.setAddress(dto.getAddress());

        candidate.setPersonalInfo(personal);
        repository.save(candidate);
    }

    public void updateBankInfo(Long id, BankInfoDTO dto) {
        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        BankInfo bank = new BankInfo();
        bank.setBankName(dto.getBankName());
        bank.setBankAccountNumber(dto.getAccountNumber());
        bank.setIfscCode(dto.getIfscCode());

        candidate.setBankInfo(bank);
        repository.save(candidate);
    }

    public void updateEducationalInfo(Long id, EducationalInfoDTO dto) {
        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        EducationInfo education = new EducationInfo();
        education.setDegree(dto.getDegree());
        education.setUniversity(dto.getUniversity());
        education.setGraduationYear(dto.getPassingYear());

        candidate.setEducationInfo(education);
        repository.save(candidate);
    }
}
