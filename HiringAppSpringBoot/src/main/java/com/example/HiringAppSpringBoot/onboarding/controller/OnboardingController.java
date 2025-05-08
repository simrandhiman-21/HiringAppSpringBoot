package com.example.HiringAppSpringBoot.onboarding.controller;

import com.example.HiringAppSpringBoot.onboarding.producer.CandidateEventProducer;
import org.springframework.web.bind.annotation.RequestMapping;

public class OnboardingController {
    private final CandidateEventProducer candidateEventProducer;

    public OnboardingController(CandidateEventProducer candidateEventProducer) {
        this.candidateEventProducer = candidateEventProducer;
    }

    @RequestMapping("/onboard")
    public String onboardCandidate(String candidateEmail) {
        // Triggering the onboarding event by sending a message to RabbitMQ
        candidateEventProducer.sendOnboardingEvent(candidateEmail);
        return "Candidate onboarding event triggered for " + candidateEmail;
    }
}
