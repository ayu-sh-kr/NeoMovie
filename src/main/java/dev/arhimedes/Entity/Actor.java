package dev.arhimedes.Entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Person")
public class Actor {
    @Id
    public final String name;

    @Relationship("ACTED_IN")
    public final List<Movie> movies;

    @Relationship("DIRECTED")
    public final List<Movie> movieList;

    public Actor(String name, List<Movie> movies, List<Movie> movieList) {
        this.name = name;
        this.movies = movies;
        this.movieList = movieList;
    }
}
