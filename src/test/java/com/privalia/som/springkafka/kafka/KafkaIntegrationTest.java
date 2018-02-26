package com.privalia.som.springkafka.kafka;

import com.privalia.som.springkafka.kafka.consumer.KafkaConsumerAck;
import com.privalia.som.springkafka.kafka.consumer.KafkaConsumerBasic;
import com.privalia.som.springkafka.kafka.producer.KafkaProducer;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaIntegrationTest {

    private final static String TEST_TOPIC = "first_topic";

    @ClassRule
    public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, true, TEST_TOPIC);

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumerBasic kafkaConsumerBasic;

    @Autowired
    private KafkaConsumerAck kafkaConsumerAck;

//    @Before
//    public void runBeforeTestMethod() throws Exception {
//        // Wait until all the partitions are assigned
//        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
//            ContainerTestUtils.waitForAssignment(messageListenerContainer, kafkaEmbedded.getPartitionsPerTopic());
//        }
//    }

    @Test
    public void testProducerConsumer() throws Exception {
        kafkaProducer.send(TEST_TOPIC, "Hello Spring Kafka!");
        kafkaConsumerBasic.getLatch().await(1000, TimeUnit.MILLISECONDS);
        assertThat(kafkaConsumerBasic.getLatch().getCount()).isEqualTo(0);
        assertThat(kafkaConsumerBasic.getMessages().size() == 1);
        assertThat(kafkaConsumerAck.getMessages().size() == 1);
    }
}
