package com.example.service;

import com.example.entity.PersonalInfo;
import com.example.entity.BankInfo;
import com.example.entity.EducationalInfo;

public interface CandidateInfoService {
    void updatePersonalInfo(Long candidateId, PersonalInfo request);
    void updateBankInfo(Long candidateId, BankInfo request);
    void updateEducationalInfo(Long candidateId, EducationalInfo request);
    void updateOnboardStatus(Long candidateId, boolean onboarded);

}
