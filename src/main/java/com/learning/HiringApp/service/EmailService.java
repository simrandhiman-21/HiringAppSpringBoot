package com.learning.HiringApp.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendMailWithTemplate(String to, String subject, Map<String, Object> model) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        // Set email details
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("simrandhiman3121@gmail.com"); // Replace with your Gmail address

        // Process Thymeleaf template
        Context context = new Context();
        context.setVariables(model);
        String htmlContent = templateEngine.process("Mail", context); // Assumes you have a Thymeleaf template named email-template.html

        helper.setText(htmlContent, true);

        // Send email
        mailSender.send(mimeMessage);
    }
}