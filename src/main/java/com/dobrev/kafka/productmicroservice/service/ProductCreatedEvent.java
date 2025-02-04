package com.dobrev.kafka.productmicroservice.service;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductCreatedEvent(
        String productId,
        String title,
        BigDecimal price,
        Integer quantity
) {}