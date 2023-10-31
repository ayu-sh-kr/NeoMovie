package dev.arhimedes;

import dev.arhimedes.repository.ActorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.arhimedes")
public class NeoMovieApplication{

    private final ActorRepository actorRepository;

    public NeoMovieApplication(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(NeoMovieApplication.class, args);
    }

}
