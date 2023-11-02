package dev.arhimedes.controller;

import dev.arhimedes.Entity.User;
import dev.arhimedes.dto.RegistrationRequest;
import dev.arhimedes.repository.EmailVerificationRepository;
import dev.arhimedes.repository.UserRepository;
import dev.arhimedes.service.MailVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/register")
public class RegisterController {

    private final PasswordEncoder encoder;

    private final UserRepository repository;

    private final EmailVerificationRepository emailVerificationRepository;

    private final MailVerificationService mailVerificationService;

    public RegisterController(UserRepository repository, PasswordEncoder encoder, EmailVerificationRepository emailVerificationRepository, MailVerificationService mailVerificationService) {
        this.repository = repository;
        this.encoder = encoder;
        this.emailVerificationRepository = emailVerificationRepository;
        this.mailVerificationService = mailVerificationService;
    }

    @PostMapping("")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest){
        log.info(registrationRequest.toString());
        if(registrationRequest != null){
            if(repository.existsByEmail(registrationRequest.getEmail())){
                return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
            }
            User user = new User(registrationRequest.getName(),
                    registrationRequest.getEmail(),
                    encoder.encode(registrationRequest.getPassword())
                    );
            repository.save(user);
            return new ResponseEntity<>("User Created Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Some error occurred", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/emailVerification")
    public ResponseEntity<?> emailVerification(@RequestBody Map<String, String> verificationToken){
        int token = Integer.parseInt(verificationToken.get("token"));
        String email = verificationToken.get("email");

        if(emailVerificationRepository.findTokenByEmail(email).isPresent()
                && emailVerificationRepository.findByEmail(email).get().isNotExpired()
                && emailVerificationRepository.findTokenByEmail(email).get() == token){
            // verify account once email is verified

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/getVerificationToken")
    public ResponseEntity<?> getToken(@RequestBody Map<String, String> map){
        if(map.get("email") != null){
            mailVerificationService.sendMail(map.get("email"));
            return new ResponseEntity<>("Verified Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/user")
    public String getUserPage(){
        return "<h1>Hello User</h1>";
    }
}
