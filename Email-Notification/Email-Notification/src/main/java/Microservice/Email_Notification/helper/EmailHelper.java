package Microservice.Email_Notification.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.HashMap;

@Component
public class EmailHelper {

    private final Logger LOGGER = LoggerFactory.getLogger(EmailHelper.class);

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String senderEmailOfficial;

    public boolean sendEmail(String toMail, String subject, HashMap<String,String> body, String template) {
        try {
            Context context = new Context();
            body.forEach((key,value)->{
                context.setVariable(key,value);
            });
            String htmlBody = templateEngine.process(template, context);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmailOfficial);
            helper.setTo(toMail);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true indicates the html content
            mailSender.send(message);
            LOGGER.info(String.format("%s message mail is sent to -> %s",template,toMail));
            return true;
        } catch (Exception e) {
            LOGGER.error(String.format("Runtime exception -> %s occurred for %s",e.getMessage(),toMail));
            e.printStackTrace();
            return false;
        }
    }

//    public void sendEmailWithAttachment(String toMail, String subject, String body, MultipartFile attachment)
//            throws MessagingException {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom("mnar27361@gmail.com");
//            helper.setTo(toMail);
//            helper.setText(body);
//            helper.setSubject(subject);
//
//            helper.addAttachment(attachment.getOriginalFilename(), new ByteArrayResource(attachment.getBytes()));
//
//            mailSender.send(message);
//
//            System.out.println("Message with attachment mail is sent to: " + toMail);
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.out.println("Mail is not send ");
//            e.printStackTrace();
//        }
//    }
}