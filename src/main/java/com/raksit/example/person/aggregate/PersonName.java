package com.raksit.example.person.aggregate;

import java.util.Objects;

public class PersonName {

  private String firstName;
  private String lastName;

  public PersonName(String firstName, String lastName) {
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = Objects.requireNonNull(lastName);
  }
}
