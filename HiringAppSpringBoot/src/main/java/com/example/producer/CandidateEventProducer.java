package com.example.producer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CandidateEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public CandidateEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOnboardingEvent(String candidateEmail) {
        String message = "Onboarding event for " + candidateEmail;
        rabbitTemplate.convertAndSend("candidate-onboarding-exchange", "candidate.onboarding", message);
        System.out.println("Sent message: " + message);
    }
}

