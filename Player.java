public class Player {

  Card card1;
  Card card2;

  int money = 500;
  int moneyOnTable = 0;

  public Player (Card card1, Card card2) {
    this.card1 = card1;
    this.card2 = card2;
  }

  public void printCards() {
    System.out.println("Player 1's cards:");
    System.out.print("|" + card1.toString() + "|  ");
    System.out.print("|" + card2.toString() + "|  ");
    System.out.println("\n");
    System.out.println("Player 1's money: " + this.money + "\n");
  }

  public void addMoney (int value) {
    this.money += value;
  }

  public int subMoney (int value) {
    this.money -= value;
    this.moneyOnTable += value;
    return value;
  }

  public int raise (int value) {
    this.money -= value;
    this.moneyOnTable += value;
    return value;
  }

  public void check() {
  }

  public int higherRank() {
    if (this.card1.rank > this.card2.rank) {
      return card1.rank;
    }
    return card2.rank;
  }
}
