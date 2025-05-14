    package com.learning.HiringApp.service;

    import com.learning.HiringApp.authDto.OtpMessage;
    import jakarta.mail.MessagingException;
    import org.springframework.stereotype.Service;

    @Service
    public class OtpMessageService {
        private final EmailService emailService;

        public OtpMessageService(final EmailService emailService) {
            this.emailService = emailService;
        }

        public void sendOtpEmail(OtpMessage otpMessage) {
            try {
                emailService.sendOtpMail(otpMessage);
            } catch (MessagingException e) {
                e.printStackTrace(); // or handle it properly
            }
        }


    }
