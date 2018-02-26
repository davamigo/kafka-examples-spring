package com.privalia.som.springkafka.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Kafka Consumer object: Read messages to the Kafka cluster
 *
 * @author david.amigo
 */
public class KafkaConsumerAck {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerAck.class);

    /**
     * Messages list
     */
    private static List<String> messages;

    /**
     * Default constructor
     */
    public KafkaConsumerAck() {
        messages = new ArrayList<>();
    }

    /**
     * @return the messages
     */
    public static List<String> getMessages() {
        return messages;
    }

    /**
     * Kafka listener
     *
     * @param payload the payload
     */
    @KafkaListener(topics = "${kafka.topic.first_topic}", containerFactory = "kafkaListenerContainerFactoryAck")
    public void listen(String payload, Acknowledgment ack) {
        LOGGER.debug(this.getClass().getName() + " - Message Received - payload: [{}]", payload);
        messages.add(payload);
        ack.acknowledge();
    }
}
