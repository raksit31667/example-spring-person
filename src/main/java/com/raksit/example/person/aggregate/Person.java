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

  public Person(PersonId personId, PersonName personName) {
    this.personId = Objects.requireNonNull(personId);
    updateName(personName);
  }

  public void updateName(PersonName newName) {
    this.personName = Objects.requireNonNull(newName);
    if (!this.personName.hasSameName(newName)) {
      this.personName = new PersonName(newName.getFirstName().toUpperCase().trim(), newName.getLastName().toUpperCase().trim());
    }
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
