package dev.arhimedes.repository;

import dev.arhimedes.Entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    @Query(value = "SELECT token FROM email_verification WHERE email = :email", nativeQuery = true)
    Optional<Integer> findTokenByEmail(@Param("email") String email);

    @Modifying
    @Query("DELETE FROM email_verification e WHERE e.deletionTimeStamp < :currentTimeStamp")
    void deleteExpiredTokens(@Param("currentTimeStamp")LocalDateTime currentTimeStamp);


    Optional<EmailVerification> findByEmail(String email);
}
