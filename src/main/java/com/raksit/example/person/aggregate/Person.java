package com.raksit.example.person.aggregate;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {

  @Id
  private final PersonId personId;

  @Version
  private String version;

  private PersonName personName;
  private Address address;
  private EmailAddress email;

  public Person(PersonId personId, PersonName personName) {
    this.personId = Objects.requireNonNull(personId);
    this.personName = Objects.requireNonNull(personName);
  }

  public void setPersonName(PersonName personName) {
    this.personName = Objects.requireNonNull(personName);
  }

  public void setStreetAddress(Address address) {
    this.address = address;
  }

  public void setEmail(EmailAddress email) {
    this.email = email;
  }
}