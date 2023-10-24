package dev.arhimedes.controller;


import dev.arhimedes.Entity.Actor;
import dev.arhimedes.Entity.Movie;
import dev.arhimedes.repository.ActorRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActorController {

    private final ActorRepository actorRepository;

    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping("/search/year/{born}")
    public List<Actor> getActorsByBorn(@PathVariable("born") Integer born){
        return actorRepository.findAllByBorn(born);
    }

    @GetMapping("/search/name/{name}")
    public List<Movie> getMoviesListByName(@PathVariable("name") String name){
        Actor actor = actorRepository.findById(name).get();
        return actor.getDirectedIn();
    }

    @GetMapping("/search/name")
    public Actor getActor(@Param("personName") String personName){
        return actorRepository.customQuery(personName);
    }

    @GetMapping("/search/actor/{name}")
    public Actor getActorDetails(@PathVariable("name") String name){
        return actorRepository.findById(name).get();
    }

    @GetMapping("/search/text/{name}")
    public List<Actor> getActorByText(@PathVariable("name") String name){
        return actorRepository.getActorByNameStartsWith(name);
    }
}
