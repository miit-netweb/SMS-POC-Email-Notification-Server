package Microservice.Email_Notification.repository;


import Microservice.Email_Notification.entity.EmailPending;
import Microservice.Email_Notification.entity.EmailSuccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSuccessRepository extends JpaRepository<EmailSuccess,Long> {
}
