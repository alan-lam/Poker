import java.util.*;

public class Play {

  static Player player1;
  static Player cpu;
  static Table table;
  static GameManager gameManager = new GameManager();
  static boolean gameOver = false;

  public static void main(String[] args) {

    while (!gameOver) {
      /* setup */
      ArrayList<Card> deck = gameManager.makeDeck();
      player1 = new Player(deck.get(0), deck.get(1));
      cpu = new Player(deck.get(2), deck.get(3));
      table = new Table(deck.get(4), deck.get(5), deck.get(6), deck.get(7), deck.get(8));
      gameManager.clearScreen();
      
      /* start game */
      round1(true);
      round2();
      round3();
      gameOver = true;
    }
  }

  public static void round1(boolean p1GoesFirst) {
    int raiseCounter = 0; // raise 3 times before ending round
    printBoard(true);
    if (p1GoesFirst) {
      System.out.println("P1 pays small blind of $10");
      System.out.println("CPU pays big blind of $20\n\n");
      gameManager.printDelay(3000);
      player1.subMoney(10);
      cpu.subMoney(20);
      table.addP1Money(10);
      table.addCPUMoney(20);
      while (raiseCounter < 3) {
        printBoard(true);
        String p1Move = promptUser();
        String cpuMove = getCPUMove();
        System.out.println("CPU's move: " + cpuMove + "\n");
        gameManager.printDelay(3000);
        if (table.getMoneyFromCPU() - table.getMoneyFromP1() == 0 && cpuMove.equals("call 0")) {
          System.out.println("Round 1 done");
          return;
        }
        if (p1Move.equals("raise") && cpuMove.substring(0,5).equals("raise")) {
          raiseCounter++;
        }
      }
    }
    System.out.println("Round 1 done");
  }

  public static void round2() {
    System.out.println("Round 2 done");
  }

  public static void round3() {
    System.out.println("Round 3 done");
  }

  /**
   * hideCpuCards: if true, cpu cards will be hidden
   */
  public static void printBoard(boolean hideCpuCards) {
    player1.printMoney(false);
    cpu.printMoney(true);
    table.printMoney();
    System.out.println("\n");
    cpu.printCards(hideCpuCards, true);
    table.printCards(5);
    player1.printCards(false, false);
  }

  public static String promptUser() {
    int difference = table.getMoneyFromCPU() - table.getMoneyFromP1();
    int raise = 50 + difference;

    System.out.println("Enter your choice: (r)aise " + raise + ", (c)all " + difference + ", (f)old, (q)uit");

    /*get user choice*/
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
        System.out.println("Fold");
      }
      /* quit */
      else if (input.equalsIgnoreCase("q")) {
        System.exit(1);
      }
    }
    return "";
  }

  public static String getCPUMove() {
    int difference = table.getMoneyFromP1() - table.getMoneyFromCPU();
    int raise = 50 + difference;

    Random random = new Random();
    int n = random.nextInt(101);
    if (n < 100) {
      cpu.subMoney(raise);
      table.addCPUMoney(raise);
      return "raise " + raise;
    }
    cpu.subMoney(difference);
    table.addCPUMoney(difference);
    return "call " + difference;
  }
}
