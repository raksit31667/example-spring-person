package com.raksit.example.person.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonTaxIssuedEvent {

  private String identificationNumber;
  private BigDecimal amount;
  private String currencyCode;
  private final LocalDateTime timestamp = LocalDateTime.now();

  public PersonTaxIssuedEvent() {
    // Default constructor for Kafka deserialization
  }

  public String getIdentificationNumber() {
    return identificationNumber;
  }

  public void setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
