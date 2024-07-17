package Microservice.Email_Notification.dto;

public class MessageDto {
    private String email;
    private String message;
    private long memId;
    private int code;

    public MessageDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getMemId() {
        return memId;
    }

    public void setMemId(long memId) {
        this.memId = memId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", memId=" + memId +
                ", code=" + code +
                '}';
    }
}
