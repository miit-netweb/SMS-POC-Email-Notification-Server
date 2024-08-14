package Microservice.Email_Notification.controller;

import Microservice.Email_Notification.dto.EmailStatus;
import Microservice.Email_Notification.publisher.EmailProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class dummyController {

    @Autowired
    private EmailProducer emailProducer;

    @GetMapping("/temp")
    public String tempmethod(){
        EmailStatus emailStatus = new EmailStatus(LocalDate.now().toString(), "CHECKING");
        emailProducer.sendMessage(emailStatus);
        return "sent";
    }

}
