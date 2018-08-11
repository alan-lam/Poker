import java.util.*;

public class GameManager {
  private Hashtable<Card, Boolean> cards = new Hashtable<Card, Boolean>();
  private Random random = new Random();

  public ArrayList<Card> makeDeck() {
    ArrayList<Card> deck = new ArrayList<Card>();

    while (deck.size() < 9) {
      int rank = random.nextInt(13);
      int suit = random.nextInt(4);
      Card card = new Card(rank, suit);
      
      if (!cards.containsKey(card)) {
        cards.put(card, true);
        deck.add(card);
      }
    }
    return deck;
  }

  public void printDeck() {
    ArrayList<Card> deck = makeDeck();
    for (int i = 0; i < 9; i++) {
      System.out.println(deck.get(i));
    }
  }
}
