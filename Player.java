public class Player {
  private Card card1;
  private Card card2;
  private int money = 2000;

  public void setCards(Card card1, Card card2) {
    this.card1 = card1;
    this.card2 = card2;
  }

  /**
   * hidden: if true, hide CPU cards with "?"
   * cpu: if true, print CPU's cards
   */
  public void printCards(boolean hidden, boolean cpu) {
    if (cpu) {
      System.out.println("CPU's cards:");
    }
    else {
      System.out.println("Your cards:");
    }
    if (hidden) {
      System.out.print("|?|  ");
      System.out.print("|?|");
    }
    else {
      System.out.print("|" + this.card1.toString() + "|  ");
      System.out.print("|" + this.card2.toString() + "|");
    }
    System.out.println("\n");
  }

  /**
   * cpu: if true, print CPU's money 
   */
  public void printMoney(boolean cpu) {
    if (cpu) {
      System.out.println("CPU's Money: $" + this.money);
    }
    else {
      System.out.println("Your Money: $" + this.money);
    }
  }

  public int getMoney() {
    return this.money;
  }

  public void addMoney(int money) {
    this.money += money;
  }

  public void subMoney(int money) {
    this.money -= money;
  }
}
