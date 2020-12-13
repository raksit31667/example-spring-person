package com.raksit.example.person.aggregate;

import java.util.Objects;

public class PersonName {

  private String firstName;
  private String lastName;

  public PersonName(String firstName, String lastName) {
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = Objects.requireNonNull(lastName);
  }

  public boolean hasSameName(PersonName otherName) {
    return otherName.firstName.toLowerCase().trim().equals(firstName.toUpperCase().trim()) &&
        otherName.lastName.toLowerCase().trim().equals(lastName.toUpperCase().trim());
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
