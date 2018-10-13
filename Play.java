import java.util.*;

public class Play {

  static Player player1;
  static Player cpu;
  static Table table;
  static GameManager gameManager;

  public static void main(String[] args) {

    /* setup */
    gameManager = new GameManager();
    ArrayList<Card> deck = gameManager.makeDeck();
    player1 = new Player(deck.get(0), deck.get(1));
    cpu = new Player(deck.get(2), deck.get(3));
    table = new Table(deck.get(4), deck.get(5), deck.get(6), deck.get(7), deck.get(8));
    
    /* start game */
    printBoard(false);
    gameManager.printDelay(3000);
    printBoard(true);
    promptUser("call");
  }

  /**
   * hideCpuCards: if true, cpu cards will be hidden
   */
  public static void printBoard(boolean hideCpuCards) {
    gameManager.clearScreenAndMoveDown();
    player1.printMoney(false);
    cpu.printMoney(true);
    table.printMoney();
    System.out.println("\n");
    cpu.printCards(hideCpuCards, true);
    table.printCards(5);
    player1.printCards(false, false);
  }

  /**
   * callOrCheck: used to tell user to call or check
   */
  public static void promptUser(String callOrCheck) {
    /* option to call */
    if (callOrCheck.equals("call")) {
      System.out.println("Enter your choice: (r)aise 50, (c)all " + (table.getMoneyFromCPU() - table.getMoneyFromP1()) + ", (f)old, (q)uit");
    }
    /* option to check */
    else {
      System.out.println("Enter your choice: (r)aise 50, (c)heck, (f)old, (q)uit");
    }
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
      /*raise*/
      if (input.equalsIgnoreCase("r")) {
        //table.addMoney(player1.subMoney (50));
      }
      /*call or check*/
      else if (input.equalsIgnoreCase("c")) {
        if (callOrCheck.equals("call")) {
          System.out.println("Call");
        }
        else {
          System.out.println("Check");
        }
      }
      /*fold*/
      else if (input.equalsIgnoreCase("f")) {
        System.out.println("Fold");
      }
      /*quit*/
      else if (input.equalsIgnoreCase("q")) {
        System.exit(1);
      }
    }
  }
}
