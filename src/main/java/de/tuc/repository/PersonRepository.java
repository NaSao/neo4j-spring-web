package de.tuc.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.tuc.domain.Person;

public interface PersonRepository extends GraphRepository<Person> {

    Person findByName(String name);
}
