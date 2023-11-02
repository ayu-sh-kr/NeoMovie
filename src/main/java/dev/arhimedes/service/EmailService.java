package dev.arhimedes.service;

public interface EmailService {
    void sendSimpleMail(String to, String subject, String text);

    void sendMailWithAttachment(String to, String subject, String text, String pathToAttachment);


}
