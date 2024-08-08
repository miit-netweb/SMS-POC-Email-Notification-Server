package Microservice.Email_Notification.controller;

import Microservice.Email_Notification.consumer.RabbitMQConsumer;
import Microservice.Email_Notification.dto.MessageDto;
import Microservice.Email_Notification.publisher.RabbitMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.AlgorithmConstraints;

@RestController
public class MessageController {

    private RabbitMQProducer rabbitMQProducer;
    private final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }



    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto message){
        LOGGER.info("Email message is about to be sent to rabbitMQ exchange.");
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message Sent");
    }
}
