package com.example.HiringAppSpringBoot.onboarding.controller;

import com.example.HiringAppSpringBoot.onboarding.dto.JobOfferNotificationDTO;
import com.example.HiringAppSpringBoot.onboarding.service.NotificationProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
public class CandidateNotificationController {

        private final NotificationProducer notificationService;

    public CandidateNotificationController(NotificationProducer notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/{id}/notify-job-offer")
    public ResponseEntity<String> notifyJobOffer(@PathVariable Long id, @RequestBody JobOfferNotificationDTO emailDTO) {
        emailDTO.setCandidateId(id); // just to be safe
        notificationService.sendJobOffer(emailDTO);
        return ResponseEntity.ok("Job offer notification queued.");
    }
}
