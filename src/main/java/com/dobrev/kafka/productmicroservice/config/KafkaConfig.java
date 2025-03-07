package com.dobrev.kafka.productmicroservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("${product-service.kafka.topic}")
    private String productCreatedTopic;
    @Value("${product-service.kafka.delete-topic}")
    private String productDeleteTopic;
    @Value("${product-service.kafka.topic-replication-factor}")
    private Integer topicReplicationFactor;
    @Value("${product-service.kafka.topic-partitions}")
    private Integer topicPartitions;

    @Bean
    NewTopic productCreatedTopic(){
        return TopicBuilder.name(productCreatedTopic)
                .partitions(topicReplicationFactor)
                .replicas(topicPartitions)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }

    @Bean
    NewTopic productDeleteTopic(){
        return TopicBuilder.name(productDeleteTopic)
                .partitions(topicReplicationFactor)
                .replicas(topicPartitions)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}