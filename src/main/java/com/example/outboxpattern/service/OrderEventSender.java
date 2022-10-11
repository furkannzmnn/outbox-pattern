package com.example.outboxpattern.service;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class OrderEventSender {
    private static final String ORDER_CREATED_TOPIC = "order-created";

    private final KafkaProducer kafkaProducer = KafkaProducer.of();

    public void sendOrderCreatedEvent(String event) {
        kafkaProducer.send(ORDER_CREATED_TOPIC, event);
    }

    @Getter
    private static class KafkaProducer {
        private String topic;
        private String message;

        public static KafkaProducer of() {
            return new KafkaProducer();
        }

        public void send(String topic, String message) {
            this.topic = topic;
            this.message = message;
        }
    }
}
