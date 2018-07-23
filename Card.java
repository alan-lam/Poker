public class Card implements Comparable<Card> {

  int rank = 0;
  int suit = 0;
  String[] rankArray = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
  String[] suitArray = {"Hearts", "Diamonds", "Clubs", "Spades"};

  public Card (int rank, int suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public boolean equals (Card card) {
    if (this.rank == card.rank && this.suit == card.suit) {
      return true;
    }
    return false;
  }

  @Override
  public int compareTo (Card card) {
    if (this.rank < card.rank) {
      return -1;
    }
    else if (this.rank == card.rank) {
      return 0;
    }
    else {
      return 1;
    }
  }

  @Override
  public boolean equals (Object o) {
    if ( ((Card)o).rank == this.rank && ((Card)o).suit == this.suit) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = this.rank + this.suit;
    return result;
  }

  public String toString() {
    return rankArray[this.rank] + " " + suitArray[this.suit];
  }
}
