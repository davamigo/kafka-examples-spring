package com.privalia.som.springkafka.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * The Kafka Producer object: Sends messages to the Kafka cluster
 *
 * @author david.amigo
 */
@Component
public class KafkaProducer {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    /**
     * The Kafka template for executing high-level operations
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Empty constructor
     */
    public KafkaProducer() { }

    /**
     * Sends a message to the provided topic with no key or partition.
     *
     * @param topic the topic
     * @param payload the data
     */
    public void send(String topic, String payload) {
        log(topic, null, null, payload);
        kafkaTemplate.send(topic, payload);
    }

    /**
     * Sends a message to the provided topic with the provided key and no partition.
     *
     * @param topic the topic
     * @param key the key
     * @param payload the data
     */
    public void send(String topic, String key, String payload) {
        log(topic, null, key, payload);
        kafkaTemplate.send(topic, key, payload);
    }

    /**
     * Sends a message to the provided topic with the provided partition and no key.
     *
     * @param topic the topic
     * @param partition the partition
     * @param payload the data
     */
    public void send(String topic, int partition, String payload) {
        log(topic, partition, null, payload);
        kafkaTemplate.send(topic, partition, payload);
    }

    /**
     * Sends a message to the provided topic with the provided key and partition.
     *
     * @param topic the topic
     * @param partition the partition
     * @param key the key
     * @param payload the data
     */
    public void send(String topic, int partition, String key, String payload) {
        log(topic, partition, key, payload);
        kafkaTemplate.send(topic, partition, key, payload);
    }

    /**
     * Sends a message to the provided record
     *
     * @param record the topic, partition, key and payload
     */
    public void send(ProducerRecord<String, String> record) {
        log(record.topic(), record.partition(), record.key(), record.value());
        kafkaTemplate.send(record);
    }

    /**
     * Log the send call
     *
     * @param topic the topic od null
     * @param partition the partition or null
     * @param key the key or null
     * @param payload the payload or null
     */
    private void log(String topic, Integer partition, String key, String payload) {
        LOGGER.debug(
            this.getClass().getName() + " - Message Sent - Topic: [{}] partition: [{}] key: [{}] payload: [{}]",
            topic,
            partition,
            key,
            payload
        );
    }
}
