package com.dobrev.kafka.productmicroservice.dto;

public record ProductDeleteEvent(
        String productId,
        Integer quantity
) {}