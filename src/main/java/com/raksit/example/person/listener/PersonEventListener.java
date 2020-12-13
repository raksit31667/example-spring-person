package com.raksit.example.person.listener;

import com.neovisionaries.i18n.CountryCode;
import com.raksit.example.person.aggregate.Address;
import com.raksit.example.person.aggregate.Person;
import com.raksit.example.person.aggregate.PersonId;
import com.raksit.example.person.aggregate.PersonName;
import com.raksit.example.person.event.PersonCreatedEvent;
import com.raksit.example.person.event.PersonNameUpdatedEvent;
import com.raksit.example.person.repository.PersonRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class PersonEventListener {

  private final PersonRepository personRepository;

  public PersonEventListener(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @KafkaListener(topics = "${kafka.consume-topics.person-created}", containerFactory = "personCreatedKafkaListenerContainerFactory")
  public void onPersonCreated(PersonCreatedEvent event) {
    Person newPerson = new Person(new PersonId(event.getIdentificationNumber()), new PersonName(event.getFirstName(), event.getLastName()));
    newPerson.setStreetAddress(new Address(event.getStreetAddress(), event.getCity(), CountryCode.getByCode(event.getCountry())));
    personRepository.save(newPerson);
  }

  @KafkaListener(topics = "${kafka.consume-topics.person-name-updated}", containerFactory = "personNameUpdatedKafkaListenerContainerFactory")
  @Retryable(value = {DuplicateKeyException.class, OptimisticLockingFailureException.class})
  public void onPersonNameUpdated(PersonNameUpdatedEvent event) {
    personRepository.findById(new PersonId(event.getIdentificationNumber()))
        .ifPresent(person -> {
          person.setPersonName(new PersonName(event.getFirstName(), event.getLastName()));
          personRepository.save(person);
        });
  }
}
