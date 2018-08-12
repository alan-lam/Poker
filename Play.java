import java.util.*;

public class Play {
  public static void main (String[] args) {

    /* setup */
    GameManager gameManager = new GameManager();
    ArrayList<Card> deck = gameManager.makeDeck();
    Player player1 = new Player(deck.get(0), deck.get(1));
    Player cpu = new Player(deck.get(2), deck.get(3));
    Table table = new Table(deck.get(4), deck.get(5), deck.get(6), deck.get(7), deck.get(8));
    
    /* start game */
    gameManager.clearScreenAndMoveDown();
    cpu.printCards(false, true);
    table.printCards(5);
    player1.printCards(false, false);
  }
}
