package com.example.controller;

import com.example.service.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @PostMapping("/{id}/notify-job-offer")
    public ResponseEntity<Void> notifyJobOffer(@PathVariable Long id) {
        jobOfferService.notifyCandidate(id);
        return ResponseEntity.ok().build();
    }
}
