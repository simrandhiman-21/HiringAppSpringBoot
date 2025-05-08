    package com.example.HiringAppSpringBoot.onboarding.service;

    import com.example.HiringAppSpringBoot.onboarding.config.RabbitMQConfig;
    import com.example.HiringAppSpringBoot.onboarding.dto.JobOfferNotificationDTO;
    import lombok.RequiredArgsConstructor;
    import org.springframework.amqp.rabbit.core.RabbitTemplate;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class NotificationProducer {

        private final RabbitTemplate rabbitTemplate;

        public void sendJobOffer(JobOfferNotificationDTO notification) {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,   // Use the correct constant
                    RabbitMQConfig.ROUTING_KEY,    // Use the correct routing key
                    notification                   // The message payload
            );
        }
    }
