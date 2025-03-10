package com.dobrev.kafka.productmicroservice.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductCreatedEvent(
        String productId,
        String title,
        BigDecimal price,
        Integer quantity
) {}