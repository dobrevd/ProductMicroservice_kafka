package com.dobrev.kafka.productmicroservice.dto;

public record ProductReservedEvent(
        String productId,
        Integer quantity
) {}