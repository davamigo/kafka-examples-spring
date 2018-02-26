package com.privalia.som.springkafka.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Kafka common configuration
 *
 * @author david.amigo
 */
@Configuration
public class KafkaConfig {

    /**
     * CSV host:port list used for establishing the initial connections to the Kafka Cluster
     */
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Kafka Config empty constructor
     */
    public KafkaConfig() { }

    /**
     * @return the Kafka bootstrap servers as a CSV host:port string.
     */
    protected String bootstrapServers() {
        return bootstrapServers;
    }
}
