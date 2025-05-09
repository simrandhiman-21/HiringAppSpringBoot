package com.example.service.impl;

import com.example.dto.JobOfferMessage;
import com.example.entity.Candidate;
import com.example.exception.CandidateNotFoundException;
import com.example.producer.JobOfferProducer;
import com.example.repository.CandidateRepository;
import com.example.service.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobOfferServiceImpl implements JobOfferService {

    private final CandidateRepository candidateRepository;
    private final JobOfferProducer producer;

    @Override
    public void notifyCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + candidateId + " not found"));

        JobOfferMessage message = JobOfferMessage.builder()
                .candidateId(candidate.getId())
                .fullName(candidate.getFullName())
                .email(candidate.getEmail())
                .jobTitle("Software Engineer") // this could be dynamic later
                .build();

        producer.sendJobOffer(message);
    }
}
