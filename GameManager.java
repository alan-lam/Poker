import java.util.*;
import java.io.IOException;

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

  public void clearScreenAndMoveDown() {
    String os = System.getProperty("os.name");
    try {
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      }
      else {
        Runtime.getRuntime().exec("clear");
      }
    }
    catch(IOException | InterruptedException ex) {}
    
    for (int i = 0; i < 5; i++) {
      System.out.println("\n");
    }
  }
}
