package dev.arhimedes.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "email_verification")
@Table(name = "email_verification")
public class EmailVerification {


    @Id
    @Column(unique = true)
    private String email;
    private int token;

    private LocalDateTime deletionTimeStamp;

    public EmailVerification() {
    }

    public EmailVerification(String email, int token) {
        this.email = email;
        this.token = token;
        this.deletionTimeStamp = LocalDateTime.now().plusMinutes(2);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public LocalDateTime getDeletionTimeStamp() {
        return deletionTimeStamp;
    }

    public void setDeletionTimeStamp(LocalDateTime deletionTimeStamp) {
        this.deletionTimeStamp = deletionTimeStamp;
    }

    public boolean isNotExpired(){
        if(deletionTimeStamp.isBefore(LocalDateTime.now())){
            return false;
        }
        return true;
    }

}
