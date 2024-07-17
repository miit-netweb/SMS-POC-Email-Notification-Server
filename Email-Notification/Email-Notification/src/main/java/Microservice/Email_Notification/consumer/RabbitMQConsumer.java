package Microservice.Email_Notification.consumer;

import Microservice.Email_Notification.dto.MessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(MessageDto message){
        System.out.println(String.format("The massage received"));
        System.out.println(message);
    }
}
