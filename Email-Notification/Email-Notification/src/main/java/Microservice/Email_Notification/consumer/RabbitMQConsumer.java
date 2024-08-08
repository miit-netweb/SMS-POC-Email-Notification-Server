package Microservice.Email_Notification.consumer;

import Microservice.Email_Notification.entity.EmailPending;
import Microservice.Email_Notification.entity.EmailSuccess;
import Microservice.Email_Notification.helper.EmailHelper;
import Microservice.Email_Notification.service.EmailService;
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
    private EmailService emailService;
    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(EmailPending message){
        try {

            HashMap<String,String> body = new HashMap<>();
            body.put("SubscriberNumber", message.getSubscriberNumber());
//            if(emailHelper.sendEmail(message.getEmail(), emailTemplateFromCode.getSubject(),body,emailTemplateFromCode.getTemplateName()))
            boolean b = emailHelper.sendEmail(message.getEmailId(), "WELCOME_SMS_APPLICATION", body, "welcome_template");
            if(b)
            {
                LOGGER.info("Email has been sent properly for {}", message.getSubscriberNumber());
                emailService.removePendingEntry(message);
                LOGGER.info("Email entry removed from email-pending-table for {}",message.getSubscriberNumber());
                emailService.addSuccessEntry(new EmailSuccess(message.getSubscriberNumber(),message.getEmailId(),message.getCode(),"EMAIL_SUCCESS"));
                LOGGER.info("Email entry inserted into email-success-table for {}",message.getSubscriberNumber());
            }
        } catch (Exception e){
            LOGGER.error("Runtime exception occurred {} for memId {}", e.getMessage(), message.getSubscriberNumber());
        }
    }
}
 
