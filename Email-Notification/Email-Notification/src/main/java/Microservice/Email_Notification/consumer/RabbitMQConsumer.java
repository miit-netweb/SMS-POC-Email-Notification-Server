package Microservice.Email_Notification.consumer;

import Microservice.Email_Notification.dto.EmailStatus;
import Microservice.Email_Notification.dto.TemplateInfo;
import Microservice.Email_Notification.entity.EmailPending;
import Microservice.Email_Notification.entity.EmailSuccess;
import Microservice.Email_Notification.helper.EmailHelper;
import Microservice.Email_Notification.publisher.EmailProducer;
import Microservice.Email_Notification.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

@Service
public class RabbitMQConsumer {
    @Autowired
    private EmailHelper emailHelper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailProducer emailProducer;
    private final HashMap<Integer, TemplateInfo> codeToTemplateMapper = new HashMap<>();

    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    public RabbitMQConsumer() {
        codeToTemplateMapper.put(800, new TemplateInfo("WELCOME_SMS_APPLICATION","welcome_template"));
        codeToTemplateMapper.put(801, new TemplateInfo("UPDATE_SMS_INFORMATION","update_template"));
    }

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(EmailPending message){
        try {
            // dynamic value for template
            // Todo generic hashmap for template
            HashMap<String,String> body = new HashMap<>();
            body.put("SubscriberNumber", message.getSubscriberNumber());
                TemplateInfo templateInfo = codeToTemplateMapper.get(message.getCode());
                boolean b = emailHelper.sendEmail(message.getEmailId(), templateInfo.getSubject(), body,templateInfo.getTemplateName());
                if(b)
                {
                    LOGGER.info("Email has been sent properly for {}", message.getSubscriberNumber());
                    emailService.removePendingEntry(message);
                    LOGGER.info("Email entry removed from email-pending-table for {}",message.getSubscriberNumber());
                    emailService.addSuccessEntry(new EmailSuccess(message.getSubscriberNumber(),message.getEmailId(),message.getCode(),"EMAIL_SUCCESS"));
                    LOGGER.info("Email entry inserted into email-success-table for {}",message.getSubscriberNumber());
                    emailProducer.sendMessage(new EmailStatus(LocalDateTime.now().toString(),"EMAIL-SUCCESS"));
                }
                else {
                    emailProducer.sendMessage(new EmailStatus(LocalDateTime.now().toString(),"EMAIL-FAILURE"));
                }
        } catch (Exception e){
            LOGGER.error("Runtime exception occurred {} for memId {}", e.getMessage(), message.getSubscriberNumber());
        }
    }
}
 
