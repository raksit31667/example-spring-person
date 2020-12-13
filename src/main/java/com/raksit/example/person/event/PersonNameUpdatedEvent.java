package com.raksit.example.person.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonNameUpdatedEvent {

  private String identificationNumber;
  private String firstName;
  private String lastName;
  private final LocalDateTime timestamp = LocalDateTime.now();

  public PersonNameUpdatedEvent() {
  }

  public String getIdentificationNumber() {
    return identificationNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
