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
      
      round1();
      gameOver = true;
      round2();
      round3();
    }
  }

  public static void round1() {
    printBoard(false);
    gameManager.printDelay(3000);
    player1.subMoney(10);
    cpu.subMoney(20);
    table.addP1Money(10);
    table.addCPUMoney(20);
    printBoard(true);
    promptUser();
    String cpuMove = getCPUMove();
    System.out.println("CPU's move: " + cpuMove);
    gameManager.printDelay(3000);
    printBoard(true);
    promptUser();
  }

  public static void round2() {
    // 4th card
  }

  public static void round3() {
    // 5th card
  }

  /**
   * hideCpuCards: if true, cpu cards will be hidden
   */
  public static void printBoard(boolean hideCpuCards) {
    //gameManager.clearScreenAndMoveDown();
    player1.printMoney(false);
    cpu.printMoney(true);
    table.printMoney();
    System.out.println("\n");
    cpu.printCards(hideCpuCards, true);
    table.printCards(5);
    player1.printCards(false, false);
  }

  public static void promptUser() {
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
      }
      /* call */
      else if (input.equalsIgnoreCase("c")) {
        player1.subMoney(difference);
        table.addP1Money(difference);
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
  }

  public static String getCPUMove() {
    int difference = table.getMoneyFromP1() - table.getMoneyFromCPU();
    int raise = 50 + difference;

    Random random = new Random();
    int n = random.nextInt(101);
    System.out.println(n);
    if (n < 51) {
      cpu.subMoney(raise);
      table.addCPUMoney(raise);
      return "raise " + raise;
    }
    cpu.subMoney(difference);
    table.addCPUMoney(difference);
    return "call " + difference;
  }
}
