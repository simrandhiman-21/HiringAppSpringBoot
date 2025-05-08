package com.example.HiringAppSpringBoot.onboarding.listner;

import com.example.HiringAppSpringBoot.onboarding.config.RabbitMQConfig;
import com.example.HiringAppSpringBoot.onboarding.dto.JobOfferNotificationDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobOfferEmailListener {

    private final JavaMailSender mailSender;

    // Listen to RabbitMQ queue for new job offer notifications
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listen(JobOfferNotificationDTO notification) {
        log.info("Received Job Offer Notification for: {}", notification.getCandidateEmail());
        try {
            sendEmailWithRetry(notification);
        } catch (MessagingException e) {
            log.error("Failed to send email after retries for: {}", notification.getCandidateEmail(), e);
        }
    }

    // Retryable method to send email with automatic retry on failure
    @Retryable(
            value = { MessagingException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public void sendEmailWithRetry(JobOfferNotificationDTO notification) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            // Set the recipient email address, subject, and body
            helper.setTo(notification.getCandidateEmail());
            helper.setSubject("Job Offer - " + notification.getPosition());

            String body = "<h3>Hi " + notification.getCandidateName() + ",</h3>"
                    + "<p>Congratulations! You have been selected for the position of <strong>" + notification.getPosition() + "</strong>.</p>"
                    + "<p>Welcome aboard!</p>";

            helper.setText(body, true);

            // Send the email
            mailSender.send(message);
            log.info("Email sent to: {}", notification.getCandidateEmail());

        } catch (MessagingException e) {
            log.error("Error sending email to {}: {}", notification.getCandidateEmail(), e.getMessage());
            throw e; // Re-throw exception to trigger retry
        }
    }
}
