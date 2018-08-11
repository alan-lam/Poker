import java.util.*;

public class Play {
  public static void main (String[] args) {
    GameManager gameManager = new GameManager();
    ArrayList<Card> deck = gameManager.makeDeck();
    Player player1 = new Player(deck.get(0), deck.get(1));
    Player cpu = new Player(deck.get(2), deck.get(3));
    gameManager.printDeck();
  }
}
