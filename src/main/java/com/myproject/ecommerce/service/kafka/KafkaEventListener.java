package com.myproject.ecommerce.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.ecommerce.domain.OrderCreatedEvent;
import com.myproject.ecommerce.domain.entity.ProcessedEvent;
import com.myproject.ecommerce.repository.OrderRepository;
import com.myproject.ecommerce.repository.ProcessedEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class KafkaEventListener {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProcessedEventRepository processedEventRepository;

    @KafkaListener(topics = "order.events", groupId = "order-service")
    public void handleOrderCreated(String eventJson) throws JsonProcessingException {
        OrderCreatedEvent event = new ObjectMapper().readValue(eventJson, OrderCreatedEvent.class);

        // 1. Check for idempotency
        if (processedEventRepository.existsById(event.getEventId())) {
            return; // Skip duplicate event
        }

        // 2. Perform business logic
        orderRepository.updateStatus(event.getOrderId(), "PROCESSING");

        // 3. Mark as processed
        processedEventRepository.save(new ProcessedEvent(event.getEventId(), Instant.now()));
    }
}