package com.example.producer;

import com.example.config.RabbitMQConfig;
import com.example.dto.JobOfferMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobOfferProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendJobOffer(JobOfferMessage message) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.JOB_OFFER_EXCHANGE,
                RabbitMQConfig.JOB_OFFER_ROUTING_KEY,
                message
        );
    }
}
