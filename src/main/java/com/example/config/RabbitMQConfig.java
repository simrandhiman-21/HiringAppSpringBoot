package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String JOB_OFFER_QUEUE = "job_offer_queue";
    public static final String JOB_OFFER_EXCHANGE = "job_offer_exchange";
    public static final String JOB_OFFER_ROUTING_KEY = "job_offer_key";

    @Bean
    public Queue jobOfferQueue() {
        return new Queue(JOB_OFFER_QUEUE, true);
    }

    @Bean
    public TopicExchange jobOfferExchange() {
        return new TopicExchange(JOB_OFFER_EXCHANGE);
    }

    @Bean
    public Binding jobOfferBinding() {
        return BindingBuilder.bind(jobOfferQueue())
                .to(jobOfferExchange())
                .with(JOB_OFFER_ROUTING_KEY);
    }
}
