package com.learning.HiringApp.service;

import com.learning.HiringApp.dtos.CandidateDTO;
import com.learning.HiringApp.entity.Candidate;
import com.learning.HiringApp.enums.CandidateStatus;
import com.learning.HiringApp.exceptions.CandidateNotFoundException;
import com.learning.HiringApp.exceptions.InvalidStatusTransitionException;
import com.learning.HiringApp.exceptions.NotFoundException;
import com.learning.HiringApp.exceptions.ResourceNotFoundException;
import com.learning.HiringApp.repository.CandidateRepository;
import com.learning.HiringApp.mapper.CandidateMapper;
import com.learning.HiringApp.service.producer.DocumentProducer;
import com.learning.HiringApp.service.producer.RabbitProducer;

import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private DocumentProducer documentProducer;

    @Autowired
    private RabbitProducer rabbitProducer;

    @Autowired
    private CandidateMapper candidateMapper;

    //getall
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAllCandidates();

        for (Candidate candidate : candidates) {
            if (candidate.getStatus() != null) {
                switch (candidate.getStatus()) {
                    case OFFERED:
                    case REJECTED:
                        log.info("Sending status mail for Candidate ID: {}", candidate.getId());
                        rabbitProducer.sendCandidate(candidateMapper.toDto(candidate));
                        break;
                    default:
                        log.info("Skipping Candidate ID: {} with status: {}", candidate.getId(), candidate.getStatus());
                }
            }
        }

        return candidates;
    }

    //getbyid

        public Candidate findById(Long id) {
            return candidateRepository.findById(id).orElseThrow(() -> new NotFoundException("Candidate not found"));
        }

    //add
    @CachePut(value = "candidates", key = "#result.id")
    public Candidate addCandidate(Candidate candidate) throws Exception {
        if (candidateRepository.existsById(candidate.getId())) {
            throw new Exception("Candidate already exists");
        }

        // Set default status to APPLIED only if no status is provided
        if (candidate.getStatus() == null) {
            candidate.setStatus(CandidateStatus.APPLIED);
        }

        Candidate saved = candidateRepository.save(candidate);

        log.info("Candidate saved with ID: {}", saved.getId());
        documentProducer.sendCandidateId(saved.getId());

        return saved;
    }


    //count
    public long countCandidates() {
        return candidateRepository.count();
    }


    public List<Candidate> getCandidatesByStatus(String status) {
        try {
            return candidateRepository.findByStatus(CandidateStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

}
