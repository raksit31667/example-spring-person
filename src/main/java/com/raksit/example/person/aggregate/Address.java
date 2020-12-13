package com.raksit.example.person.aggregate;

import com.neovisionaries.i18n.CountryCode;
import java.util.Objects;

public class Address {

  private String street;
  private String city;
  private CountryCode countryCode;

  public Address(String street, String city, CountryCode countryCode) {
    this.street = Objects.requireNonNull(street);
    this.city = Objects.requireNonNull(city);
    this.countryCode = Objects.requireNonNull(countryCode);
  }
}
