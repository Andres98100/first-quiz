package org.velezreyes.quiz.question6;

import java.util.List;
import java.util.ArrayList;

public class VendingMachineImpl implements VendingMachine {

  public int balance;
  public List<Drink> availableDrinks;

  public VendingMachineImpl() {
    this.balance = 0;
    this.availableDrinks = new ArrayList<Drink>();
    this.availableDrinks.add(new ScottCola());
    this.availableDrinks.add(new KarenTea());
  }

  @Override
  public void insertQuarter() {
    this.balance += 25;
  }

  @Override
  public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
    Drink selectedDrink = null;

    for (Drink drink : availableDrinks) {
      if (drink.getName().equals(name)) {
        selectedDrink = drink;
        break;
      }
    }

    if (selectedDrink == null) {
      throw new UnknownDrinkException();
    }

    int price = selectedDrink.getPrice();
    if (balance >= price) {
      balance -= price;
      return selectedDrink;
    } else {
      throw new NotEnoughMoneyException();
    }
  }

  private static class ScottCola implements Drink {

    @Override
    public String getName() {
      return "ScottCola";
    }

    @Override
    public boolean isFizzy() {
      return true;
    }

    @Override
    public int getPrice() {
      return 75;
    }

  }

  private static class KarenTea implements Drink {

    @Override
    public String getName() {
      return "KarenTea";
    }

    @Override
    public boolean isFizzy() {
      return false;
    }

    @Override
    public int getPrice() {
      return 100;
    }
  }

  public static VendingMachine getInstance() {
    return new VendingMachineImpl();
  }
}
