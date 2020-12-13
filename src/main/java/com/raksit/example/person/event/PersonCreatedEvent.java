package com.raksit.example.person.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonCreatedEvent {

  private String identificationNumber;
  private String firstName;
  private String lastName;
  private final LocalDateTime timestamp = LocalDateTime.now();

  public PersonCreatedEvent() {
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

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
  }

}
