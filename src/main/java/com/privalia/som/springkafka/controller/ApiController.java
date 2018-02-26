package com.privalia.som.springkafka.controller;

import com.privalia.som.springkafka.model.Message;
import com.privalia.som.springkafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API controller
 *
 * @author david.amigo
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    /**
     * The Kafka service
     */
    @Autowired
    KafkaService kafkaService;

    /**
     * Posts a message to Kafka
     *
     * @param message the message: text + key (optional)
     */
    @PostMapping("/message")
    public void postMessage(Message message) {
        kafkaService.send(message);
    }

    /**
     * Returns the messages read from a consumer
     *
     * @param consumer the name of the consumer: auto or manual
     * @return a list of messages
     */
    @GetMapping("/message")
    public List<Message> getMessages(
        @RequestParam(value="consumer", required=false, defaultValue="auto") String consumer
    ) {
        List<Message> messages;
        switch (consumer) {
            default:
            case "auto":
            case "basic":
            case "latch":
                messages = kafkaService.getMessagesBasic();
                break;

            case "manual":
            case "ack":
                messages = kafkaService.getMessagesAck();
                break;
        }
        return messages;
    }
}
