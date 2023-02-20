package org.example.leetcode.problems._1_dataStructure.hashtable;

/**
 * 2347.最好的扑克手牌 <br>
 * 开题时间：2023-02-20 09:42:44
 */
public class BestPokerHand {
  public static void main(String[] args) {
    Solution solution = new BestPokerHand().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public String bestHand(int[] ranks, char[] suits) {
      if (isAllSame(suits)) {
        return "Flush";
      }
      
      int[] freq = new int[14];
      for (int x : ranks) {
        if (++freq[x] >= 3) {
          return "Three of a Kind";
        }
      }
      for (int x : ranks) {
        if (freq[x] >= 2) {
          return "Pair";
        }
      }
      
      return "High Card";
    }
    
    public static boolean isAllSame(char[] arr) {
      for (int i = 1; i < arr.length; i++) {
        if (arr[i] != arr[0]) {
          return false;
        }
      }
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}