package Microservice.Email_Notification.repository;


import Microservice.Email_Notification.entity.EmailPending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailPendingRepository extends JpaRepository<EmailPending,Long> {
}
