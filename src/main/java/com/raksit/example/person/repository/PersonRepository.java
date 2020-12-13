package com.raksit.example.person.repository;

import com.raksit.example.person.aggregate.Person;
import com.raksit.example.person.aggregate.PersonId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, PersonId> {

}
