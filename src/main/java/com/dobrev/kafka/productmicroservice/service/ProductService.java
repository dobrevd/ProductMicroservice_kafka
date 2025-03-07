package com.dobrev.kafka.productmicroservice.service;
import com.dobrev.kafka.core.ProductCreatedEvent;

import com.dobrev.kafka.productmicroservice.dto.ProductDto;
import com.dobrev.kafka.productmicroservice.dto.ProductDeleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    @Value("${product-service.kafka.topic}")
    private String productCreatedTopic;
    @Value("${product-service.kafka.delete-topic}")
    private String productDeleteTopic;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public String createProduct(ProductDto productDto){
        var productId = UUID.randomUUID().toString();
        var productEvent = createProductCreatedEvent(productDto, productId);

        var record = new ProducerRecord<>(productCreatedTopic, productId, productEvent);
        record.headers().add("messageId", productEvent.productId().getBytes());

        sendProductEventToKafka(productCreatedTopic, productId, productEvent);

        return productId;
    }

    public void deleteProduct(String productId, Integer quantity) {
        var productDeleteEvent = new ProductDeleteEvent(productId,  quantity);
        sendProductEventToKafka(productDeleteTopic, productId, productDeleteEvent);

        log.info("Sent event about product reservation {}", productId);
    }

    private void sendProductEventToKafka(String topic, String productId, Object productEvent) {
        kafkaTemplate.send(topic, productId, productEvent)
                .whenComplete((result, exception) -> {
                    if (exception != null){
                        log.error("Failed to send message: {}", exception.getMessage());
                    } else{
                        log.info("Message sent successfully: {}", result.getRecordMetadata());
                    }
                });
    }

    private ProductCreatedEvent createProductCreatedEvent(ProductDto productDto, String productId) {
        return ProductCreatedEvent.builder()
                .productId(productId)
                .title(productDto.title())
                .price(productDto.price())
                .quantity(productDto.quantity())
                .build();
    }
}