package dev.arhimedes.Entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Movie {
    @Id
    public final String title;

    public String tagline;

    public Integer released;


    public Movie(String title, String tagline, Integer released) {
        this.title = title;
        this.tagline = tagline;
        this.released = released;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }
}
