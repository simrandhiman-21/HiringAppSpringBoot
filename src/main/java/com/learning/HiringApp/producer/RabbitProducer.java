package com.learning.HiringApp.producer;

import com.learning.HiringApp.config.RabbitMQMail;
import com.learning.HiringApp.dtos.CandidateDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCandidate(CandidateDTO candidateDTO) {
        System.out.println("Sending candidate: " + candidateDTO);
        if ("offered".equalsIgnoreCase(candidateDTO.getStatus()) ||
                "rejected".equalsIgnoreCase(candidateDTO.getStatus())) {
            rabbitTemplate.convertAndSend(
                    RabbitMQMail.EXCHANGE,
                    RabbitMQMail.ROUTING_KEY,
                    candidateDTO
            );
            System.out.println("Message sent to RabbitMQ");
        }
    }
}