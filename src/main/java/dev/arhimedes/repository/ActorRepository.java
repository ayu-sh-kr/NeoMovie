package dev.arhimedes.repository;

import dev.arhimedes.Entity.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ActorRepository extends Neo4jRepository<Actor, String> {

}
