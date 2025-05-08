package com.example.service;

import com.example.dto.CandidateDTO;
import com.example.dto.CreateCandidateRequest;

import java.util.List;

public interface CandidateService {
    List<CandidateDTO> getAllHiredCandidates();
    CandidateDTO getCandidateById(Long id);
    long getHiredCandidateCount();
    CandidateDTO createCandidate(CreateCandidateRequest request);


}
