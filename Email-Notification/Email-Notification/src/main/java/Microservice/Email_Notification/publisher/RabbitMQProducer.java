package Microservice.Email_Notification.publisher;

import Microservice.Email_Notification.dto.MessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String EXCHANGE_NAME;

    @Value("${rabbitmq.routing.key}")
    private String ROUTING_KEY;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDto message){
        System.out.println(String.format("Message sent -> %s",message));
        try {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,ROUTING_KEY,message);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
