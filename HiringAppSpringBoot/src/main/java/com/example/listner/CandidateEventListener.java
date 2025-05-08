//package com.example.HiringAppSpringBoot.onboarding.listner;
//
//import com.example.HiringAppSpringBoot.onboarding.service.EmailService;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CandidateEventListener {
//
//    private final EmailService emailService;
//
//    public CandidateEventListener(EmailService emailService) {
//        this.emailService = emailService;
//    }
//
//    @RabbitListener(queues = "candidate-onboarding-queue")
//    public void listenForOnboardingEvent(String message) {
//        System.out.println("Received message: " + message);
//
//        // Extract candidate email from the message and send a welcome email
//        String candidateEmail = extractEmailFromMessage(message);
//
//        // Customize the email content based on the event
//        String subject = "Welcome to the Onboarding System!";
//        String body = "<h1>Welcome!</h1><p>Your onboarding process has started. Please check your dashboard.</p>";
//
//        // Send email
//        emailService.sendEmail(candidateEmail, subject, body);
//    }
//
//    private String extractEmailFromMessage(String message) {
//        // Dummy method: Extract the email from the event message.
//        // In real scenarios, you'd parse the JSON message and extract details.
//        return "candidate@example.com";
//    }
//}
//
//
//
