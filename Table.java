public class Table {
  private Card card1;
  private Card card2;
  private Card card3;
  private Card card4;
  private Card card5;
  private int moneyFromP1 = 0;
  private int moneyFromCPU = 0;

  public void setCards(Card c1, Card c2, Card c3, Card c4, Card c5) {
    this.card1 = c1;
    this.card2 = c2;
    this.card3 = c3;
    this.card4 = c4;
    this.card5 = c5;
  }

  public int getMoneyFromP1() {
    return this.moneyFromP1;
  }

  public int getMoneyFromCPU() {
    return this.moneyFromCPU;
  }

  public void addP1Money(int money) {
    this.moneyFromP1 += money;
  }

  public void addCPUMoney(int money) {
    this.moneyFromCPU += money;
  }

  public void clearTableMoney() {
    this.moneyFromP1 = 0;
    this.moneyFromCPU = 0;
  }

  /**
   * numberOfCards: number of cards to display on table
   */
  public void printCards(int numberOfCards) {
    if (numberOfCards == 0) {
      System.out.println("\n");
    }
    else if (numberOfCards == 3) {
      System.out.print("|" + card1.toString() + "|  ");
      System.out.print("|" + card2.toString() + "|  ");
      System.out.print("|" + card3.toString() + "|");
      System.out.println("\n");
    }
    else if (numberOfCards == 4) {
      System.out.print("|" + card1.toString() + "|  ");
      System.out.print("|" + card2.toString() + "|  ");
      System.out.print("|" + card3.toString() + "|  ");
      System.out.print("|" + card4.toString() + "|");
      System.out.println("\n");
    }
    else if (numberOfCards == 5) {
      System.out.print("|" + card1.toString() + "|  ");
      System.out.print("|" + card2.toString() + "|  ");
      System.out.print("|" + card3.toString() + "|  ");
      System.out.print("|" + card4.toString() + "|  ");
      System.out.print("|" + card5.toString() + "|");
      System.out.println("\n");
    }
  }

  public void printMoney() {
    System.out.println("Money on table: $" + (moneyFromCPU + moneyFromP1));
  }
}
