package com.raksit.example.person.event;

import com.raksit.example.person.aggregate.Money;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class PersonMoneyChangelog {

  private Money money;
  private String reason;

  public PersonMoneyChangelog(Money money, String reason) {
    this.money = Objects.requireNonNull(money);
    this.reason = reason;
  }

  public Money getMoney() {
    return money;
  }
}
