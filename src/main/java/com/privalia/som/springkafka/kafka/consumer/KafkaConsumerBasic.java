package com.privalia.som.springkafka.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * The Kafka Consumer object: Read messages to the Kafka cluster
 *
 * @author david.amigo
 */
@Component
public class KafkaConsumerBasic {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerBasic.class);

    /**
     * Messages list
     */
    private static List<String> messages;

    /**
     * Latch
     */
    private CountDownLatch latch;

    /**
     * Default constructor
     */
    public KafkaConsumerBasic() {
        messages = new ArrayList<>();
        latch = new CountDownLatch(1);
    }

    /**
     * @return the messages
     */
    public static List<String> getMessages() {
        return messages;
    }

    /**
     * @return the latch
     */
    public CountDownLatch getLatch() {
        return latch;
    }

    /**
     * Kafka listener
     *
     * @param payload the payload
     */
    @KafkaListener(topics = "${kafka.topic.first_topic}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String payload) {
        LOGGER.debug(this.getClass().getName() + " - Message Received - payload: [{}]", payload);
        messages.add(payload);
        latch.countDown();
    }
}
