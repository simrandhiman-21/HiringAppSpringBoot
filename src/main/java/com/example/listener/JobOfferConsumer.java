package com.example.listener;

import com.example.dto.JobOfferMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import org.thymeleaf.spring6.SpringTemplateEngine;

import static com.example.config.RabbitMQConfig.JOB_OFFER_QUEUE;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobOfferConsumer {

    private final JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @RabbitListener(queues = JOB_OFFER_QUEUE)
    public void listen(JobOfferMessage message) {
        try {
            Context context = new Context();
            context.setVariable("name", message.getFullName());
            context.setVariable("position", message.getJobTitle());

            String htmlContent = templateEngine.process("offer-email", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(message.getEmail());
            helper.setSubject(" Your Job Offer");
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
            log.info("Job offer email sent to {}", message.getEmail());

        } catch (MessagingException e) {
            log.error("Failed to send job offer email to {}", message.getEmail(), e);
        }
    }
}
