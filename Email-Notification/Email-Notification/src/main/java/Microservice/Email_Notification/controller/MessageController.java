package Microservice.Email_Notification.controller;

import Microservice.Email_Notification.dto.MessageDto;
import Microservice.Email_Notification.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private RabbitMQProducer rabbitMQProducer;

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }



    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto message){
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message Send");
    }
}
