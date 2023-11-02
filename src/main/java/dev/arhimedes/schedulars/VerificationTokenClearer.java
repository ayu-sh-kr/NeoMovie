package dev.arhimedes.schedulars;

import dev.arhimedes.repository.EmailVerificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VerificationTokenClearer {

    @Autowired
    private EmailVerificationRepository repository;

    @Transactional
    @Scheduled(fixedRate = 2 * 60 * 1000)
    public void clearExpireTokens(){
        System.out.println(LocalDateTime.now() + " Clearing tokens");
        LocalDateTime currentTimeStamp = LocalDateTime.now();
        repository.deleteExpiredTokens(currentTimeStamp);
    }
}
