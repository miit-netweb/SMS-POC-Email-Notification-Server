package Microservice.Email_Notification.publisher;

import Microservice.Email_Notification.dto.EmailStatus;
import Microservice.Email_Notification.dto.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

    private KafkaTemplate<String, EmailStatus> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(EmailProducer.class);

    public EmailProducer(KafkaTemplate<String, EmailStatus> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EmailStatus emailStatus){
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.findAndRegisterModules();
//        try {
//            // Convert POJO to JSON string
//            String jsonString = objectMapper.writeValueAsString(billingPending);
//
//            Message<String> message = MessageBuilder
//                    .withPayload(jsonString)
//                    .setHeader(KafkaHeaders.TOPIC,"billing")
//                    .build();
//
//            LOGGER.info("Message is ready to be produced and sent to kafka topic." +
//                    " message : {}",message);
//
//            kafkaTemplate.send(message);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        kafkaTemplate.send("email-status",emailStatus);

    }

}

