package Microservice.Email_Notification.consumer;

import Microservice.Email_Notification.dto.MessageDto;
import Microservice.Email_Notification.entity.EmailTemplate;
import Microservice.Email_Notification.helper.EmailHelper;
import Microservice.Email_Notification.service.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RabbitMQConsumer {
    @Autowired
    private EmailHelper emailHelper;
    @Autowired
    private EmailTemplateService emailTemplateService;
    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(MessageDto message){
        try {
            LOGGER.info("The message has been dequeued properly for "+ message.getMemId());
            int emailCode = message.getCode();
            LOGGER.info("Email template fetch initiated for "+ message.getMemId()+" code "+message.getCode());
            final EmailTemplate emailTemplateFromCode = emailTemplateService.getEmailTemplateFromCode(emailCode);
            LOGGER.info("Email template has been fetch successfully for "+ message.getMemId());
            HashMap<String,String> body = new HashMap<>();
            body.put("fname", message.getFname());
            body.put("lname", message.getLname());
            LOGGER.info("Email has been initiated for "+ message.getMemId());
            if(emailHelper.sendEmail(message.getEmail(), emailTemplateFromCode.getSubject(),body,emailTemplateFromCode.getTemplateName()))
                LOGGER.info("Email has been sent properly for "+ message.getMemId());
        } catch (Exception e){
            LOGGER.error("Runtime exception occurred "+ e.getMessage()+" for memId "+message.getMemId());
        }
    }
}
