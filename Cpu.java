import java.util.*;

public class Cpu {

   Card card1;
   Card card2;

   int money = 500;
   int moneyOnTable = 0;

   Random random = new Random();

   public Cpu (Card card1, Card card2) {
      this.card1 = card1;
      this.card2 = card2;
   }

   public void printCards (boolean hideCpuCards) {
      System.out.println("Cpu's money: " + this.money + "\n");
      System.out.println("Cpu's cards:");
      if(hideCpuCards == false) {
         System.out.print("|" + card1.toString() + "|  ");
         System.out.print("|" + card2.toString() + "|  ");
         System.out.println("\n");
      }
      else {
         System.out.println("|?|  |?|\n");
      }
   }

   public void addMoney (int value) {
      this.money += value;
   }

   public int subMoney (int value) {
      this.money -= value;
      this.moneyOnTable += value;
      return value;
   }

   public int raise (int value) {
      this.money -= value;
      this.moneyOnTable += value;
      return value;
   }

   public void check() {
      System.out.println("Player 2 checks");
   }

   public String cpuTurn (String callOrCheck) {
      int probability = random.nextInt(101);
      if (probability == 1) {
         return "f";
      }
      if (callOrCheck.equals("check")) {
         if (probability >= 2 && probability <= 51) {
            return "check";
         }
      }
      else {
         if (probability >= 2 && probability <= 51) {
            return "call";
         }
      }
      return "raise";
   }
}
