package Microservice.Email_Notification.dto;

import java.time.LocalDate;

public class EmailStatus {
    private String timeStamp;
    private String status;

    public EmailStatus() {
    }

    public EmailStatus(String timeStamp, String status) {
        this.timeStamp = timeStamp;
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "EmailStatus{" +
                "timeStamp=" + timeStamp +
                ", status='" + status + '\'' +
                '}';
    }
}
