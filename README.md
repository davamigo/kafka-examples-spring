# Kafka and Kafka Streams Proofs of Concept

## kafka-poc/kafka-examples-spring

Basic examples using spring-kafka.

* package **`com.privalia.som.springkafka.kafka.producer`**
    * class **`KafkaProducerConfig`**: Configuration for the producer.
    * class **`KafkaProducer`** The Kafka producer.


* package **`com.privalia.som.springkafka.kafka.consumer`**
    * class **`KafkaConsumerConfig`**: Configuration for the Kafka consumers.
    * class **`KafkaConsumerBasic`**: The Kafka consumer with auto commit.
    * class **`KafkaConsumerAck`**: The Kafka consumer with manual Ack.

## Web routes:

**`GET /`**: Shows the homepage.

**`POST /api/message`**: Publishes a message to the Kafka topic.

**`GET /api/message[?consumer=auto|manual]`**: Returns the messages read from a Kafka consumer.


## Configuration:
See `src/main/resources/application.yml`

* web server: **localhost:8080**
* brokers: **127.0.0.1:9092**
* topic: **first_topic**
* group ids:
    * **spring_latch**
    * **spring_ack**

