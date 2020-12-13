package com.raksit.example.person.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonCreatedEvent {

  private String identificationNumber;
  private String firstName;
  private String lastName;
  private String streetAddress;
  private String city;
  private String country;
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

  public String getStreetAddress() {
    return streetAddress;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }
}
