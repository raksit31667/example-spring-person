package com.raksit.example.person.aggregate;

import java.util.Objects;

public class Address {

  private String street;
  private String city;

  public Address(String street, String city) {
    this.street = Objects.requireNonNull(street);
    this.city = Objects.requireNonNull(city);
  }
}
