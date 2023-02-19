package org.example.leetcode.problems._1_dataStructure.hashtable;

/**
 * 1394.找出数组中的幸运数 <br>
 * 开题时间：2023-02-19 21:58:19
 */
public class FindLuckyIntegerInAnArray {
  public static void main(String[] args) {
    Solution solution = new FindLuckyIntegerInAnArray().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int findLucky(int[] arr) {
      int[] freq = new int[501];
      for (int x : arr) {
        freq[x]++;
      }
      
      for (int i = 500; i >= 1; i--) {
        if (freq[i] == i) {
          return i;
        }
      }
      
      return -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}