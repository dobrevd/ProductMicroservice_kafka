# Event-Driven Microservices with Apache Kafka

This project is built on an event-driven architecture using **Apache Kafka** and consists of two microservices:
- **Product Service** (Producer)
- **Email Notification Service** (Consumer & Producer) - [GitHub Repository](https://github.com/dobrevd/EmailNotificationMicroservice_kafka)
---

## Product Service

**Product Service** is a microservice that acts a **Kafka producer**. It sends notifications about product-related events.


### 🔹 **Core Functionality**

The microservice sends events to the following Kafka topics:
- **`${product-service.kafka.topic}`**
- **`${product-service.kafka.delete-topic}`**


## 🔹 **Kafka Producer Configuration**

| Parameter                                                                | Description                                                                      |
|--------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `server.port=0`                                                          | Allows the server to start on a random available port.                           |
| `product-service.kafka.topic`                                            | Name of the Kafka topic for product creation events.                             |
| `product-service.kafka.delete-topic`                                     | Name of the Kafka topic for product deletion events.                             |
| `product-service.kafka.topic-replication-factor`                         | Number of replicas for the Kafka topic (set to `3`).                             |
| `product-service.kafka.topic-partitions`                                 | Number of partitions for the Kafka topic (set to `3`).                           |
| `spring.kafka.producer.bootstrap-servers`                                | List of Kafka broker addresses to connect to (`localhost:9092, localhost:9094`). |
| `spring.kafka.producer.key-serializer`                                   | Serializer for message keys (`StringSerializer`).                                |
| `spring.kafka.producer.value-serializer`                                 | Serializer for message values (`JsonSerializer`).                                |
| `spring.kafka.producer.acks`                                             | Acknowledgment level (`all` ensures reliability by waiting for all replicas).    |
| `spring.kafka.producer.retries`                                          | Number of retry attempts in case of failures (`10`).                             |
| `spring.kafka.producer.properties.delivery.timeout.ms`                   | Maximum time to wait for message delivery (`12,000 ms`).                         |
| `spring.kafka.producer.properties.linger.ms`                             | Time to wait before sending messages (`0` means immediate sending).              |
| `spring.kafka.producer.properties.request.timeout.ms`                    | Timeout for waiting for a response from Kafka (`3,000 ms`).                      |
| `spring.kafka.producer.properties.enable.idempotence`                    | Enables idempotent message delivery to prevent duplicates (`true`).              |
| `spring.kafka.producer.properties.max.in.flight.requests.per.connection` | Maximum number of unacknowledged requests per connection (`5`).                  |
