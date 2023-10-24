package dev.arhimedes.Entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Person")
public class Actor {
    @Id
    public final String name;

    public final Integer born;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.OUTGOING)
    private final List<Movie> actedIn;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.OUTGOING)
    private final List<Movie> directedIn;



    public Actor(String name, Integer born, List<Movie> actedIn, List<Movie> directedIn) {
        this.name = name;
        this.born = born;
        this.actedIn = actedIn;
        this.directedIn = directedIn;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", born=" + born +
                '}';
    }

    public String getName() {
        return name;
    }

    public Integer getBorn() {
        return born;
    }

    public List<Movie> getActedIn() {
        return actedIn;
    }

    public List<Movie> getDirectedIn() {
        return directedIn;
    }
}
