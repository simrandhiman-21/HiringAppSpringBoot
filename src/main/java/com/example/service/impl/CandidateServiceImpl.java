package com.example.service.impl;

import com.example.dto.CandidateDTO;
import com.example.dto.CreateCandidateRequest;
import com.example.entity.Candidate;
import com.example.entity.CandidateStatus;
import com.example.exception.CandidateNotFoundException;
import com.example.repository.CandidateRepository;
import com.example.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public List<CandidateDTO> getAllHiredCandidates() {
        return candidateRepository.findByIsHiredTrue().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateDTO getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + id + " not found"));
        return toDto(candidate);
    }

    @Override
    public long getHiredCandidateCount() {
        return candidateRepository.countByIsHiredTrue();
    }

    private CandidateDTO toDto(Candidate candidate) {
        return CandidateDTO.builder()
                .id(candidate.getId())
                .fullName(candidate.getFullName())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .status(candidate.getStatus().name())
                .isHired(candidate.isHired())
                .build();
    }

    @Override
    public CandidateDTO createCandidate(CreateCandidateRequest request) {
        Candidate candidate = Candidate.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .status(CandidateStatus.APPLIED)
                .isHired(false)
                .build();

        Candidate saved = candidateRepository.save(candidate);
        return toDto(saved);
    }


}
