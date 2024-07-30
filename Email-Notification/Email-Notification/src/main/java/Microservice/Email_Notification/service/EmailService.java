package Microservice.Email_Notification.service;

import Microservice.Email_Notification.entity.EmailPending;
import Microservice.Email_Notification.entity.EmailSuccess;
import Microservice.Email_Notification.repository.EmailPendingRepository;
import Microservice.Email_Notification.repository.EmailSuccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailPendingRepository emailPendingRepository;
    @Autowired
    private EmailSuccessRepository emailSuccessRepository;

    public void removePendingEntry(EmailPending emailPending){
        emailPendingRepository.delete(emailPending);
    }

    public EmailSuccess addSuccessEntry(EmailSuccess emailSuccess){
        return emailSuccessRepository.save(emailSuccess);
    }

}
