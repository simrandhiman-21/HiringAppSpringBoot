package com.learning.HiringApp.consumer;

import com.learning.HiringApp.authDto.OtpMessage;
import com.learning.HiringApp.service.OtpMessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OtpListener {
    private final OtpMessageService otpMessageService;

    public OtpListener(final OtpMessageService otpMessageService) {
        this.otpMessageService = otpMessageService;
    }

    @RabbitListener(queues = "hiringAuthOtpQueue")
    public void receive(final OtpMessage message) {
        if ("auth.otp".equals(message.getRoutingKey())) {
            otpMessageService.sendOtpEmail(message);
        }
    }
}

