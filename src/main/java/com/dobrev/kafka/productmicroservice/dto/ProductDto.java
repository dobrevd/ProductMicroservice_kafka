package com.dobrev.kafka.productmicroservice.dto;

import java.math.BigDecimal;

public record ProductDto(
        String title,
        BigDecimal price,
        Integer quantity
) {}