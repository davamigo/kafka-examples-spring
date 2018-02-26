package com.privalia.som.springkafka.service;

import com.privalia.som.springkafka.kafka.consumer.KafkaConsumerAck;
import com.privalia.som.springkafka.kafka.consumer.KafkaConsumerBasic;
import com.privalia.som.springkafka.kafka.producer.KafkaProducer;
import com.privalia.som.springkafka.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaService {

    /**
     * The name of the Kafka topic to read messages
     */
    @Value("${kafka.topic.first_topic}")
    String kafkaTopic;

    /**
     * The Kafka producer
     */
    @Autowired
    KafkaProducer kafkaProducer;

    /**
     * Sends a message to the configured Kafka topic
     *
     * @param message the message to send
     */
    public void send(Message message) {
        kafkaProducer.send(kafkaTopic, message.getKey(), message.getText());
    }

    /**
     * The list of messages read by the consumer with auto commit
     *
     * @return the list of messages
     */
    public List<Message> getMessagesBasic() {
        List<Message> messages = new ArrayList<>();
        List<String> texts = KafkaConsumerBasic.getMessages();
        for (String text : texts) {
            messages.add(new Message(text));
        }
        return messages;
    }

    /**
     * The list of messages read by the consumer with manual ack
     *
     * @return the list of messages
     */
    public List<Message> getMessagesAck() {
        List<Message> messages = new ArrayList<>();
        List<String> texts = KafkaConsumerAck.getMessages();
        for (String text : texts) {
            messages.add(new Message(text));
        }
        return messages;
    }
}
