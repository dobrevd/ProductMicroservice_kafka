package com.dobrev.kafka.productmicroservice.service;
import com.dobrev.kafka.core.ProductCreatedEvent;

import com.dobrev.kafka.productmicroservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    @Value("${product-service.kafka.topic}")
    private String kafkaTopic;
    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public String createProduct(ProductDto productDto){
        var productId = UUID.randomUUID().toString();

        var productEvent = ProductCreatedEvent.builder()
                .productId(productId)
                .title(productDto.title())
                .price(productDto.price())
                .quantity(productDto.quantity())
                .build();

        sendProductEventToKafka(productId, productEvent);

        return productId;
    }

    private void sendProductEventToKafka(String productId, ProductCreatedEvent productEvent) {
         kafkaTemplate.send(kafkaTopic, productId, productEvent)
                .whenComplete((result, exception) -> {
                    if (exception != null){
                        log.error("Failed to send message: {}", exception.getMessage());
                    } else{
                        log.info("Message sent successfully: {}", result.getRecordMetadata());
                    }
                });
    }
}