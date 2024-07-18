package Microservice.Email_Notification.dto;

public class MessageDto {
    private String email;
    private String fname;
    private String lname;
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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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
                ", memId=" + memId +
                ", code=" + code +
                '}';
    }
}
