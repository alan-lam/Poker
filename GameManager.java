import java.util.*;
import java.io.IOException;

public class GameManager {

  int[] rank = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
  int[] suit = {0, 1, 2, 3};

  Deck deck = new Deck();

  Player player1 = new Player(null, null);
  Cpu player2 = new Cpu(null, null);

  Table table;

  Scanner scanner = new Scanner(System.in);
  String input = "hi";

  /**
   * Give out cards to Player 1, Player 2, and the rest on the table
   */
  public void dealCards() {

    player1.card1 = deck.cardDeck.get(0);
    player1.card2 = deck.cardDeck.get(1);

    player2.card1 = deck.cardDeck.get(2);
    player2.card2 = deck.cardDeck.get(3);

    Card card5 = deck.cardDeck.get(4);
    Card card6 = deck.cardDeck.get(5);
    Card card7 = deck.cardDeck.get(6);
    Card card8 = deck.cardDeck.get(7);
    Card card9 = deck.cardDeck.get(8);
    table = new Table(card5, card6, card7, card8, card9);
   }

  //https://stackoverflow.com/questions/2979383/java-clear-the-console
  public void clearScreen() {
    String os = System.getProperty("os.name");
    try {
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      }
      else {
        Runtime.getRuntime().exec("clear");
      }
    }
    catch(IOException | InterruptedException ex) {
    }
  

  //https://stackoverflow.com/questions/24104313/how-to-delay-in-java
  public void printDelay (int time) {
    try {
      Thread.sleep(time);
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * hideCpuCards: if it isn't end of round, don't show cpu's cards
   * cardsOnTable: number of cards to show on table (beginning of each round is 3)
   * delay: simulate flipping over cards on table (true only at beginning of each round)
   */
  public void printGame (boolean hideCpuCards, int cardsOnTable, boolean delay) {
    int index;

    /*move text to middle of screen*/
    for (int i = 0; i < 5; i++) {
      System.out.println("\n");
    }

    System.out.println("Money on the table: " + table.money 
    + " (from Player 1: " + player1.moneyOnTable + "; from CPU: " 
    + player2.moneyOnTable + ")\n");

    player2.printCards(hideCpuCards);

    System.out.println("\n");

    player1.printCards();

    /*execute if cards on table need to be printed*/
    if (cardsOnTable != 0) {

      //https://stackoverflow.com/questions/15051688/is-it-possible-to-rewrite-previous-line-in-console
      //ESC[7A - cursor up 7 times
      //\r - move cursor to beginning of line
      System.out.print("\033[7A\r");

      /*print out cards on table*/
      for (index = 0; index < cardsOnTable; index++) {
        if (delay == true) {
          printDelay(2000);
        }
        table.printTable(index);
      }

      for (; index < cardsOnTable; index++) {
        printDelay(2000);
        table.printTable(index);
      }

      System.out.println("\n");
      System.out.print("\033[6B\r");
    }
  

  public int promptUser (String callOrCheck) {

    /*option to call vs check*/
    if (callOrCheck.equals("call")) {
      System.out.println("Enter your choice: (r)aise 50, (c)all " 
      + (player2.moneyOnTable - player1.moneyOnTable) + ", (f)old, (q)uit");
    }
    /*option to check vs call*/
    else {
      System.out.println("Enter your choice: (r)aise 50, (c)heck, (f)old, (q)uit");
    }

    /*get user choice*/
    if (scanner.hasNext()) {
      input = scanner.next();
    }

    /*check for invalid input*/
    while (!input.equalsIgnoreCase("r") && !input.equalsIgnoreCase("c") && 
           !input.equalsIgnoreCase("f") && !input.equalsIgnoreCase("q")) {
      System.out.print("Invalid option. Please enter a valid option: ");
      try { 
        input = scanner.next();
      }
      catch (NoSuchElementException e) {
        System.exit(1);
      }
    }

    //int value = 0;

    /*raise*/
    if (input.equalsIgnoreCase("r")) {
      table.addMoney(player1.subMoney (50));
    }

    /*call or check*/
    else if (input.equalsIgnoreCase("c")) {
      if (callOrCheck.equals("call")) {
        table.addMoney(player1.subMoney (player2.moneyOnTable - player1.moneyOnTable));
      }
    }

    /*fold*/
    else if (input.equalsIgnoreCase("f")) {
      printDelay(2000);
      player2.addMoney (table.clearMoney());
      player1.moneyOnTable = 0;
      player2.moneyOnTable = 0;
      return 0;
    }

    /*quit*/
    else if (input.equalsIgnoreCase("q")) {
      System.exit(1);
    }
    return 1;
  }

  public void printCpuDecision (String cpuMove) {

    if (cpuMove.equals("f")) {
      System.out.println("Player 2 folds");
      player1.addMoney(table.clearMoney());
      player1.moneyOnTable = 0;
      player2.moneyOnTable = 0;
    }

    else if (cpuMove.equals("check")) {
      System.out.println("Player 2 checks");
    }

    else if (cpuMove.equals("call")) {
      System.out.println("Player 2 calls");
      table.addMoney(player2.subMoney(player1.moneyOnTable-player2.moneyOnTable));
    }

    else {
      System.out.println("Player 2 raises");
      table.addMoney(player2.subMoney(50));
    }
  }

  public Card[] gatherPlayerCards() {
    /*testing only*/ 
    Card[] cards = new Card[7];
    Card card1 =  new Card(rank[0], suit[0]);
    Card card6 = new Card(rank[1], suit[0]);
    Card card3 = new Card(rank[2], suit[1]);
    Card card7 = new Card(rank[3], suit[0]);
    Card card5 = new Card(rank[4], suit[0]);
    Card card4 = new Card(rank[9], suit[1]);
    Card card2 = new Card(rank[6], suit[2]);
    cards[0] = card1;
    cards[1] = card2;
    cards[2] = card3;
    cards[3] = card4;
    cards[4] = card5;
    cards[5] = card6;
    cards[6] = card7;
    /*testing only*/
    /*
    cards[0] = player1.card1;
    cards[1] = player1.card2;
    for(int i = 2; i < 7; i++) {
      cards[i] = table.list.get(i);
    }
    */
    return cards;
  }

  public Card[] gatherCpuCards() {
    Card[] cards = new Card[7];
    cards[0] = player2.card1;
    cards[1] = player2.card2;
    for (int i = 2; i < 7; i++) {
      cards[i] = table.list.get(i);
    }
    return cards;
  }

  public void play() {

    Random random = new Random();
    int userMove = 1;
    boolean gameOver = false;

    //while (!gameOver) {
      int turn = random.nextInt(1);
      clearScreen();
      deck.makeDeck();
      dealCards();
      printGame(true, 3, true);
    //}
  }

  public static void main (String[] args) {
    GameManager gameManager = new GameManager();
    gameManager.play();
  }
}
