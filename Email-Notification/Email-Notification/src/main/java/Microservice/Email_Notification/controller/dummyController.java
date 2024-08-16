package Microservice.Email_Notification.controller;

import Microservice.Email_Notification.dto.EmailStatus;
import Microservice.Email_Notification.publisher.EmailProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@RestController
public class dummyController {

    @Autowired
    private EmailProducer emailProducer;

    @GetMapping("/temp")
    public String tempmethod(){
        EmailStatus emailStatus = new EmailStatus(LocalDateTime.now().toString(), "EMAIL-SUCCESS");
        Random random = new Random();
        int min = 1;
        int max = 10;

        int randomNumber = random.nextInt(max - min + 1) + min;
        if(randomNumber<4){
            emailProducer.sendMessage(new EmailStatus(LocalDateTime.now().toString(),"EMAIL-FAILURE"));
        } else {
            emailProducer.sendMessage(emailStatus);
        }
        return "sent";
    }

}
