package com.myproject.ecommerce.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaDeadLetterListener {


    @KafkaListener(topics = "order.dlq", groupId = "order-dlq-handler")
    public void handleDeadLetter(String payload) {
        log.error("Dead letter received: {}", payload);
        // Optional: Store to separate table or alert ops team
    }
}
