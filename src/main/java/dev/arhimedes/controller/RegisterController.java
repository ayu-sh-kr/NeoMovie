package dev.arhimedes.controller;

import dev.arhimedes.Entity.User;
import dev.arhimedes.dto.RegistrationRequest;
import dev.arhimedes.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegisterController {

    @Autowired
    private PasswordEncoder encoder;

    private final UserRepository repository;

    public RegisterController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
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

    @GetMapping("/admin")
    public String getAdminPage(){
        return "<h1>Hello Admin</h1>";
    }

    @GetMapping("/user")
    public String getUserPage(){
        return "<h1>Hello User</h1>";
    }
}
