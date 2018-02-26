package com.privalia.som.springkafka.kafka.consumer;

import com.privalia.som.springkafka.kafka.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka configuration for consumers
 *
 * @author david.amigo
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig extends KafkaConfig {

    @Value("${kafka.consumer.group.id1}")
    private String kafkaGroupIdBasic;

    @Value("${kafka.consumer.group.id2}")
    private String kafkaGroupIdAck;

    /**
     * Default constructor
     */
    public KafkaConsumerConfig() {
        super();
    }

    /**
     * @return the configurations for the Kafka consumer with auto commit
     */
    @Bean
    public Map<String, Object> consumerConfigsBasic() {

        Map<String, Object> props = new HashMap<>();

        // list of host:port pairs used for establishing the initial connections to the Kafka cluster
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // Allows a pool of processes to divide the work of consuming and processing records
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupIdBasic);

        // Automatically reset the offset to the earliest offset
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    /**
     * @return the configurations for the Kafka consumer with manual ack
     */
    @Bean
    public Map<String, Object> consumerConfigsAck() {

        Map<String, Object> props = new HashMap<>();

        // list of host:port pairs used for establishing the initial connections to the Kafka cluster
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // Allows a pool of processes to divide the work of consuming and processing records
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupIdAck);

        // Automatically reset the offset to the earliest offset
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Disable auto commit (to allow manual ack)
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        return props;
    }

    /**
     * @return the consumer factory for the kafka consumer with auto commit
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactoryBasic() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigsBasic());
    }

    /**
     * @return the consumer factory for the kafka consumer with manual ack
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactoryAck() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigsAck());
    }

    /**
     * @return the kafka listener container factory for consumer with auto commit
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryBasic());
        return factory;
    }

    /**
     * @return the kafka listener container factory for consumer with manual ack
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactoryAck() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryAck());
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
        return factory;
    }

    /**
     * @return the Kafka consumer with auto commit
     */
    @Bean
    public KafkaConsumerBasic kafkaConsumerBasic() {
        return new KafkaConsumerBasic();
    }

    /**
     * @return the Kafka consumer with manual ack
     */
    @Bean
    public KafkaConsumerAck kafkaConsumerAck() {
        return new KafkaConsumerAck();
    }
}
