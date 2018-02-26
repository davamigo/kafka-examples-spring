package com.privalia.som.springkafka.kafka.producer;

import com.privalia.som.springkafka.kafka.KafkaConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka configuration for producers
 *
 * @author david.amigo
 */
@Configuration
public class KafkaProducerConfig extends KafkaConfig {

    /**
     * Default constructor
     */
    public KafkaProducerConfig() {
        super();
    }

    /**
     * @return the default configurations for the Kafka producer
     */
    private Map<String, Object> producerConfigs() {

        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.RETRIES_CONFIG, "3");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "1");

        return props;
    }

    /**
     * @return the default kafka producer factory
     */
    private ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    /**
     * @return a new Kafka template for producing messages
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * @return the Kafka Producer obeject
     */
    @Bean
    public KafkaProducer kafkaProducer() {
        return new KafkaProducer();
    }
}
