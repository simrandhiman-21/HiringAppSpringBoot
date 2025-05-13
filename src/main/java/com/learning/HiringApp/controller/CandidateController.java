package com.learning.HiringApp.controller;
import com.learning.HiringApp.dtos.CandidateDTO;
import com.learning.HiringApp.entity.Candidate;
import com.learning.HiringApp.exceptions.NotFoundException;
import com.learning.HiringApp.service.CandidateService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.learning.HiringApp.service.producer.RabbitProducer;


import java.time.LocalDateTime;
import java.util.List;
//2 8 12
@RestController
@RequestMapping("/api/candidates")
@Slf4j

public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private RabbitProducer rabbitProducer;

    @GetMapping("/all")
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    //getbyid
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> findById(@PathVariable Long id) {
        Candidate candidate = candidateService.findById(id);
        if (candidate == null) {
            throw new NotFoundException("Candidate with ID " + id + " not found");
        }
        return ResponseEntity.ok(candidate);
    }

    @PostMapping("/add")
    public Candidate createCandidate(@Valid @RequestBody Candidate candidate) throws Exception {
        log.info("Candidate created: " + candidate);
        return candidateService.addCandidate( candidate );
    }

//    //update
//    @PutMapping("/{id}")
//    public Candidate updateCandidate(@Valid @PathVariable Long id,@RequestBody Candidate candidate) {
//        return candidateService.updateCandidate( id, candidate );
//    }

    @GetMapping("/count")
    public long getCandidateCount() {
        return candidateService.countCandidates();
    }

    // New endpoint to send emails to all candidates with "OFFERED" status
    @GetMapping("/send-offered-emails")
    public String sendOfferedEmails() {
        List<Candidate> offeredCandidates = candidateService.getCandidatesByStatus("OFFERED");
        int sentCount = 0;

        for (Candidate candidate : offeredCandidates) {
            CandidateDTO candidateDTO = CandidateDTO.fromEntity(candidate);
            rabbitProducer.sendCandidate(candidateDTO);
            sentCount++;
            log.info("Candidate {} sent to RabbitMQ for email notification", candidateDTO.getFullName());
        }

        return "Sent email notifications for " + sentCount + " candidates with OFFERED status";
    }
}