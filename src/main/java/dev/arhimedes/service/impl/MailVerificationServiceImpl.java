package dev.arhimedes.service.impl;

import dev.arhimedes.Entity.EmailVerification;
import dev.arhimedes.repository.EmailVerificationRepository;
import dev.arhimedes.service.EmailService;
import dev.arhimedes.service.MailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class MailVerificationServiceImpl implements MailVerificationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailVerificationRepository emailVerificationRepository;

    public void saveOrUpdateRepository(String email, int token){
        Optional<EmailVerification> existByEmail = emailVerificationRepository.findByEmail(email);
        if(existByEmail.isPresent()){
            EmailVerification emailVerification = existByEmail.get();
            emailVerification.setToken(token);
            emailVerification.setDeletionTimeStamp(LocalDateTime.now().plusMinutes(2));
        }
        else{
            EmailVerification emailVerification = new EmailVerification(email, token);
            emailVerificationRepository.save(emailVerification);
        }
    }

    public static int getRandom(){
        Random random = new Random();
        int min = 100000, max = 999999;
        return random.nextInt(min, max);
    }
    @Override
    public int sendMail(String email) {
        int token = getRandom();
        saveOrUpdateRepository(email, token);
        String subject = "Email verification for Account Registration";
        String text = "Here is your 6 digit otp to verify with the 'NEOMOVIE' services " +
                token
                + " Please ignore the mail if not initiated by you";
        emailService.sendSimpleMail(email, subject, text);
        return token;
    }
}
