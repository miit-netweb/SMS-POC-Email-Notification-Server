package Microservice.Email_Notification.dto;

public class TemplateInfo {
    private final String subject;
    private final String templateName;

    public TemplateInfo(String subject, String templateName) {
        this.subject = subject;
        this.templateName = templateName;
    }

    public String getSubject() {
        return subject;
    }

    public String getTemplateName() {
        return templateName;
    }

    @Override
    public String toString() {
        return "TemplateInfo{" +
                "subject='" + subject + '\'' +
                ", templateName='" + templateName + '\'' +
                '}';
    }
}
