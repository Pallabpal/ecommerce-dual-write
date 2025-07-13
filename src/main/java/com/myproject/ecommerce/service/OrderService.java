package com.myproject.ecommerce.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.ecommerce.domain.entity.Order;
import com.myproject.ecommerce.domain.OrderCreatedEvent;
import com.myproject.ecommerce.domain.OrderRequest;
import com.myproject.ecommerce.domain.entity.OutboxEvent;
import com.myproject.ecommerce.repository.OrderRepository;
import com.myproject.ecommerce.repository.OutboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    @Transactional
    public void placeOrder(OrderRequest request) throws JsonProcessingException {
        Order order = new Order(UUID.randomUUID(), request.getCustomerId(), request.getItems(), "PLACED");
        orderRepository.save(order);

        OrderCreatedEvent orderEvent = new OrderCreatedEvent(UUID.randomUUID(), order.getId(), request.getCustomerId(), request.getItems());

        OutboxEvent event = new OutboxEvent(UUID.randomUUID(),
                "ORDER", order.getId(), "ORDER_CREATED",
                new ObjectMapper().writeValueAsString(orderEvent),
                "PENDING", Instant.now(), null);

        outboxRepository.save(event);
    }
}

