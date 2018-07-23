import java.util.*;

public class HandRanker {
   int[] rank = new int[13];
   int[] suit = new int[4];

   public Card[] sortHand (Card[] cards) {
      ArrayList<Card> sortedList = new ArrayList<Card>();
      for (int i = 0; i < cards.length; i++) {
         sortedList.add(cards[i]);
      }
      Collections.sort(sortedList);
      Card[] sortedArray = new Card[7];
      for (int j = 0; j < sortedArray.length; j++) {
         sortedArray[j] = sortedList.get(j);
      }
      return sortedArray;
   }

   public void fillSuitArray (Card[] cards) {
      for (int i = 0; i < suit.length; i++) {
         suit[i] = 0;
      }
      for (Card card : cards) {
         suit[card.suit] += 1;
      }
   }

   public void fillRankArray (Card[] cards) {
      for(int i = 0; i < rank.length; i++) {
         rank[i] = 0;
      }
      for(Card card : cards) {
         rank[card.rank] += 1;
      }
   }

   public int rankHand (Card[] cards) { 
      if (straightFlush(cards)) return 9;
      else if (fourKind(cards)) return 8;
      else if (fullHouse(cards)) return 7;
      else if (flush(cards)) return 6;
      else if (straight(cards)) return 5;
      else if (threeKind(cards)) return 4;
      else if (twoPair(cards)) return 3;
      else if (onePair(cards)) return 2;
      return 1;
   }

   public boolean straightFlush (Card[] cards) {
      if (!straight(cards) && !flush(cards)) {
         return false;
      }
      Card[] sortedCards = sortHand(cards);
      int i = 0;
      while (i < 3) {
         if (sortedCards[i].rank - sortedCards[i+1].rank == -1) {
            if (sortedCards[i+1].rank - sortedCards[i+2].rank == -1) {
               if (sortedCards[i+2].rank - sortedCards[i+3].rank == -1) {
                  if (sortedCards[i+3].rank - sortedCards[i+4].rank == -1) {
                     break;
                  }
               }
            }
         }
         i++;
      }
      if (sortedCards[i].suit == sortedCards[i+1].suit) {
         if (sortedCards[i+1].suit == sortedCards[i+2].suit) {
            if (sortedCards[i+2].suit == sortedCards[i+3].suit) {
               if (sortedCards[i+3].suit == sortedCards[i+4].suit) {
                  return true;
               }
            }
         }
      }
      return false;
   }

   public boolean fourKind (Card[] cards) {
      fillRankArray(cards);
      for (int i = 0; i < rank.length; i++) {
         if (rank[i] == 4) {
            return true;
         }
      }
      return false;
   }

   public boolean fullHouse (Card[] cards) {
      fillRankArray(cards);
      boolean flag_one = false;
      boolean flag_two = false;
      for (int i = 0; i < rank.length; i++) {
         if (rank[i] == 3 && flag_one == false) {
            flag_one = true;
         }
         else if (rank[i] >= 2) {
            flag_two = true;
         }
      }
      return (flag_one && flag_two);
   }

   public boolean flush (Card[] cards) {
      fillSuitArray(cards);
      if (suit[0] >= 5 || suit[1] >= 5 || suit[2] >= 5 || suit[3] >= 5) {
         return true;
      }
      return false;
   }

   public boolean straight (Card[] cards) {
      fillRankArray(cards);
      int i = 0;
      while (i < rank.length-4) {
         if (rank[i] >= 1 && rank[i+1] >= 1 && rank[i+2] >= 1 && rank[i+3] >= 1 && rank[i+4] >= 1) {
            return true;
         }
         i++;
      }
      /*10, J, Q, K, A*/
      if (rank[0] >=1 && rank[9] >= 1 && rank[10] >= 1 && rank[11] >= 1 && rank[12] >= 1) {
         return true;
      }
      return false;
   }

   public boolean threeKind (Card[] cards) {
      fillRankArray(cards);
      for (int i = 0; i < rank.length; i++) {
         if (rank[i] == 3) {
            return true;
         }
      }
      return false;
   }

   public boolean twoPair (Card[] cards) {
      fillRankArray(cards);
      int twoPair = 0;
      int i = 0;
      while (twoPair < 2 && i < rank.length) {
         if (rank[i] == 2) {
            twoPair++;
         }
         i++;
      }
      if (twoPair == 2) {
         return true;
      }
      return false;
   }

   public boolean onePair (Card[] cards) {
      fillRankArray(cards);
      for (int i = 0; i < rank.length; i++) {
         if (rank[i] == 2) {
            return true;
         }
      }
      return false;
   }
}
