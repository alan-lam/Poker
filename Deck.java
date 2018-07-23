import java.util.*;

public class Deck {

  ArrayList<Card> cardDeck = new ArrayList<Card>();
  Random random = new Random();

  public void makeDeck() {

    while (cardDeck.size() != 9) {

      int rank = random.nextInt(13);
      int suit = random.nextInt(4);
      Card card = new Card (rank, suit);

      /*check for duplicates*/
      while (cardDeck.contains(card)) {
        rank = random.nextInt(13);
        suit = random.nextInt(4);
        card = new Card(rank, suit);
      }

      cardDeck.add(card);
    }
  }
}
