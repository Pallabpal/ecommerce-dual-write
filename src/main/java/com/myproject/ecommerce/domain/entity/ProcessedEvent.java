package com.myproject.ecommerce.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedEvent {
    @Id
    private UUID eventId;
    private Instant processedAt;


}
