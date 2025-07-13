package com.myproject.ecommerce.domain;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private UUID eventId;
    private UUID orderId;
    private String customerId;
    private List<String> items;
 }

