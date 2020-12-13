package com.raksit.example.person.aggregate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Money {

  private final BigDecimal amount;
  private final Currency currency;

  public Money(BigDecimal amount, Currency currency) {
    this.currency = Objects.requireNonNull(currency);
    this.amount = Objects.requireNonNull(amount);
  }

  public Money add(Money income) {
    assertSameCurrency(income);
    return new Money(amount.add(income.amount), currency);
  }

  public Money subtract(Money expense) {
    assertSameCurrency(expense);
    return new Money(amount.subtract(expense.amount), currency);
  }

  private void assertSameCurrency(Money other) {
    if (!other.currency.equals(this.currency)) {
      throw new IllegalArgumentException("Money objects must have the same currency");
    }
  }
}
