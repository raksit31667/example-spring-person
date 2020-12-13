package com.raksit.example.person.aggregate;

import com.raksit.example.person.event.PersonMoneyChangelog;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {

  @Id
  private final PersonId personId;

  @Version
  private String version;

  private PersonName personName;
  private Address address;
  private Money money;
  private List<PersonMoneyChangelog> moneyChangelogs;

  public Person(PersonId personId, PersonName personName) {
    this.personId = Objects.requireNonNull(personId);
    this.moneyChangelogs = new ArrayList<>();
    initializeMoney();
    updateName(personName);
  }

  private void initializeMoney() {
    this.money = new Money(BigDecimal.ZERO, Currency.getInstance("THB"));
    earnMoney(BigDecimal.TEN, Currency.getInstance("THB"), "init");
  }

  public void updateName(PersonName newName) {
    this.personName = Objects.requireNonNull(newName);
    if (!this.personName.hasSameName(newName)) {
      this.personName = new PersonName(newName.getFirstName().toUpperCase().trim(), newName.getLastName().toUpperCase().trim());
    }
  }

  public void earnMoney(BigDecimal amount, Currency currency, String reason) {
    this.money = this.money.add(new Money(amount, currency));
    this.moneyChangelogs.add(new PersonMoneyChangelog(new Money(amount, currency), reason));
  }

  public void spendMoney(BigDecimal amount, Currency currency, String reason) {
    this.money = this.money.subtract(new Money(amount, currency));
    this.moneyChangelogs.add(new PersonMoneyChangelog(new Money(amount.negate(), currency), reason));
  }

  public Stream<Money> getTransaction() {
    return this.moneyChangelogs.stream().map(PersonMoneyChangelog::getMoney);
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
