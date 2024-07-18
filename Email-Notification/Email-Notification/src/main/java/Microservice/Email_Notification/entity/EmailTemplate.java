package Microservice.Email_Notification.entity;


import jakarta.persistence.*;

@Entity
public class EmailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String templateName;
    private String subject;
    @Column(unique = true)
    private int code;

    public EmailTemplate() {
    }

    public EmailTemplate(int id, String templateName, String subject, int code) {
        this.id = id;
        this.templateName = templateName;
        this.subject = subject;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
