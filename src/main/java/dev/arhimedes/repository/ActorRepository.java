package dev.arhimedes.repository;

import dev.arhimedes.Entity.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ActorRepository extends Neo4jRepository<Actor, String> {
    List<Actor> findAllByBorn(Integer born);

    Actor findActorByBorn(Integer born);


    @Query("MATCH(n:Person {name: $personName})-[r: DIRECTED]->(m:Movie), " +
            "(n)-[a:ACTED_IN]->(m)" +
            "RETURN n, collect(r), collect(a), collect(m)")
    Actor customQuery(@Param("personName") String personName);

    @Query("MATCH(n:Person) -[r]-> (m: Movie) " +
            "WHERE n.name STARTS WITH $name " +
            "RETURN n, collect(m), collect(r)")
    List<Actor> getActorByNameStartsWith(@PathVariable("name") String name);

}

