import java.util.*;

public class Table {
  ArrayList<Card> list = new ArrayList<Card>();
  int money = 0;

  public Table (Card card1, Card card2, Card card3, Card card4, Card card5) {
    list.add(card1);
    list.add(card2);
    list.add(card3);
    list.add(card4);
    list.add(card5);
  }

  public void printTable (int number) {
    System.out.print("|" + list.get(number).toString() + "|  ");
  }

  public void addMoney (int money) {
    this.money += money;
  }

  public int clearMoney() {
    int winnings = this.money;
    this.money = 0;
    return winnings;
  }
}
