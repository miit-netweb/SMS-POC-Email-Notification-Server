package Microservice.Email_Notification.service;

import Microservice.Email_Notification.entity.EmailTemplate;
import Microservice.Email_Notification.repository.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailTemplateService {
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    public EmailTemplate getEmailTemplateFromCode(int code){
        try{
            final Optional<EmailTemplate> emailTemplate = emailTemplateRepository.findByCode(code);
            if(emailTemplate.isEmpty()){
                return null;
            } else {
                return emailTemplate.get();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
