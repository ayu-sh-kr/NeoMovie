package dev.arhimedes;

import dev.arhimedes.Entity.Actor;
import dev.arhimedes.repository.ActorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeoMovieApplication implements CommandLineRunner {

    private final ActorRepository actorRepository;

    public NeoMovieApplication(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(NeoMovieApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Actor tomHanks = actorRepository.findById("Tom Hanks").get();
        tomHanks.movies.forEach(System.out::println);

        Actor taylorHackford = actorRepository.findById("Taylor Hackford").get();
        System.out.println("Directed in:-");
        taylorHackford.movieList.forEach(System.out::println);
        System.out.println("Acted in:-");
        taylorHackford.movies.forEach(System.out::println);
    }
}
