package Microservice.Email_Notification.publisher;

import Microservice.Email_Notification.consumer.RabbitMQConsumer;
import Microservice.Email_Notification.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDto message){
        try {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,ROUTING_KEY,message);
            LOGGER.info("email message has been queued in the exchange : {}",message);
        } catch (Exception e){
            LOGGER.error("Issue occurred in producing the email message : {}", e.getMessage());
        }
    }
}
