package com.myproject.ecommerce.service;

import com.myproject.ecommerce.domain.entity.OutboxEvent;
import com.myproject.ecommerce.repository.OutboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class OutboxPoller {

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 5000)
    public void publishEvents() {
        List<OutboxEvent> events = outboxRepository.findByStatus("PENDING");
        for (OutboxEvent event : events) {
            try {
                kafkaTemplate.send("order.events", event.getPayload());
                event.setStatus("SENT");
                event.setSentAt(Instant.now());
            } catch (Exception e) {
                kafkaTemplate.send("order.dlq", event.getPayload());
                event.setStatus("FAILED");
            } finally {
                outboxRepository.save(event);
            }
        }
    }
}