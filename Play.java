import java.util.*;

public class Play {

  static Player player1 = new Player();
  static Player cpu = new Player();
  static Table table = new Table();
  static GameManager gameManager = new GameManager();
  static boolean gameOver = false;
  static boolean p1Turn = gameManager.decideTurn();
  static final int DELAY = 5000;

  public static void main(String[] args) {

    while (!gameOver) {
      ArrayList<Card> deck = gameManager.makeDeck();
      player1.setCards(deck.get(0), deck.get(1));
      cpu.setCards(deck.get(2), deck.get(3));
      table.setCards(deck.get(4), deck.get(5), deck.get(6), deck.get(7), deck.get(8));
      gameManager.clearScreen();
      //printBoard(true, 3);
      printBoard(false, 5);
/*      
      if (p1Turn) {
        System.out.println("P1 pays small blind of $10");
        System.out.println("CPU pays big blind of $20\n\n");
        player1.subMoney(10);
        cpu.subMoney(20);
        table.addP1Money(10);
        table.addCPUMoney(20);
      }
      else {
        System.out.println("P1 pays big blind of $20");
        System.out.println("CPU pays small blind of $10\n\n");
        player1.subMoney(20);
        cpu.subMoney(10);
        table.addP1Money(20);
        table.addCPUMoney(10);
      }
      if (round(p1Turn, 1)) {
        continue;
      }
      if (round(p1Turn, 2)) {
        continue;
      }
      if (round(p1Turn, 3)) {
        continue;
      }
*/
      HandRanker hr = new HandRanker();
      int p1HandScore = hr.rankHand(gatherCards(player1));
      int cpuHandScore = hr.rankHand(gatherCards(cpu));
      System.out.println("Your score: " + p1HandScore);
      System.out.println("CPU score: " + cpuHandScore);
      gameOver = true;
    }
  }

  /**
   * roundNumber: 1, 2, or 3
   * return: true if one player folds
   */
  public static boolean round(boolean p1RoundTurn, int roundNumber) {
    /* raise 3 times before ending round */
    int p1RaiseCounter = 0;
    int cpuRaiseCounter = 0;

    gameManager.printDelay(DELAY);
    printBoard(true, roundNumber+2);

    if (p1RoundTurn) {
      while (p1RaiseCounter < 3 && cpuRaiseCounter < 3) {
        String p1Move = promptUser();
        if (p1Move.equals("fold")) {
          System.out.println("Round " + roundNumber + " done");
          gameManager.printDelay(DELAY);
          p1Turn = false;
          return true;
        }
        else if (p1Move.equals("raise")) {
          p1RaiseCounter++;
        }

        String cpuMove = getCPUMove();
        System.out.println("CPU's move: " + cpuMove + "\n");
        if (cpuMove.equals("fold")) {
          System.out.println("Round " + roundNumber + " done");
          gameManager.printDelay(DELAY);
          p1Turn = false;
          return true;
        }
        else if (cpuMove.equals("call 0")) {
          System.out.println("Round " + roundNumber + " done");
          gameManager.printDelay(DELAY);
          return false;
        }
        /* cpu raised */
        else {
          cpuRaiseCounter++;
        }
      }
    }
    else {
      while (p1RaiseCounter < 3 && cpuRaiseCounter < 3) {
        String cpuMove = getCPUMove();
        System.out.println("CPU's move: " + cpuMove + "\n");
        if (cpuMove.equals("fold")) {
          System.out.println("Round " + roundNumber + " done");
          gameManager.printDelay(DELAY);
          p1Turn = true;
          return true;
        }
        else if (cpuMove.equals("raise")) {
          cpuRaiseCounter++;
        }

        gameManager.printDelay(DELAY);
        printBoard(true, 3);

        String p1Move = promptUser();
        if (p1Move.equals("fold")) {
          System.out.println("Round " + roundNumber + " done");
          gameManager.printDelay(DELAY);
          p1Turn = true;
          return true;
        }
        else if (p1Move.equals("call 0")) {
          System.out.println("Round " + roundNumber + " done");
          gameManager.printDelay(DELAY);
          return false;
        }
        /* p1 raised */
        else {
          p1RaiseCounter++;
        }
      }
    }
    return false;
  }

  /**
   * hideCpuCards: if true, cpu cards will be hidden
   * numOfCardsFaceUp: 3, 4, or 5
   */
  public static void printBoard(boolean hideCpuCards, int numOfCardsFaceUp) {
    player1.printMoney(false);
    cpu.printMoney(true);
    table.printMoney();
    System.out.println("\n");
    cpu.printCards(hideCpuCards, true);
    table.printCards(numOfCardsFaceUp);
    player1.printCards(false, false);
  }

  /**
   * Also performs calculations for adding/subtracting money to/from player/table
   * return: user's move ("raise", "call n", "fold")
   */
  public static String promptUser() {
    int difference = table.getMoneyFromCPU() - table.getMoneyFromP1();
    int raise = 50 + difference;

    System.out.println("Enter your choice: (r)aise " + raise + ", (c)all " + difference + ", (f)old, (q)uit");

    String input;
    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNext()) {
      input = scanner.next();
      /*check for invalid input*/
      while (!input.equalsIgnoreCase("r") && !input.equalsIgnoreCase("c") && !input.equalsIgnoreCase("f") && !input.equalsIgnoreCase("q")) {
        System.out.print("Invalid option. Please enter a valid option: ");
        try { 
          input = scanner.next();
        }
        catch (NoSuchElementException e) {
          System.exit(1);
        }
      }
      /* raise */
      if (input.equalsIgnoreCase("r")) {
        player1.subMoney(raise);
        table.addP1Money(raise);
        return "raise";
      }
      /* call */
      else if (input.equalsIgnoreCase("c")) {
        player1.subMoney(difference);
        table.addP1Money(difference);
        return "call " + difference;
      }
      /* fold */
      else if (input.equalsIgnoreCase("f")) {
        cpu.addMoney(table.getMoneyFromCPU() + table.getMoneyFromP1());
        table.clearTableMoney();
        System.out.println("fold");
        return "fold";
      }
      /* quit */
      else if (input.equalsIgnoreCase("q")) {
        System.exit(1);
      }
    }
    return "";
  }

  /**
   * Also performs calculations for adding/subtracting money to/from player/table
   * return: CPU's move ("raise", "call n", "fold")
   */
  public static String getCPUMove() {
    int difference = table.getMoneyFromP1() - table.getMoneyFromCPU();
    int raise = 50 + difference;

    Random random = new Random();
    int n = random.nextInt(100);
    if (n < 49) { // 0-48 (49%)
      cpu.subMoney(raise);
      table.addCPUMoney(raise);
      return "raise " + raise;
    }
    else if (n < 99) { // 49-98 (50%)
      cpu.subMoney(difference);
      table.addCPUMoney(difference);
      return "call " + difference;
    }
    player1.addMoney(table.getMoneyFromCPU() + table.getMoneyFromP1());
    table.clearTableMoney();
    return "fold"; // 99 (1%)
  }

  public static Card[] gatherCards(Player player) {
    Card[] playerCards = player.getCards();
    Card[] tableCards = table.getCards();
    Card[] totalCards = new Card[7];
    for (int i = 0; i < 2; i++) {
      totalCards[i] = playerCards[i];
    }
    for (int i = 2; i < 7; i++) {
      totalCards[i] = tableCards[i-2];
    }
    return totalCards;
  }
}
