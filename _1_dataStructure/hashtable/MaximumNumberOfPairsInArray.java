package _1_dataStructure.hashtable;

import java.util.Arrays;

/**
 * 2341.数组能形成多少数对 <br>
 * 开题时间：2023-02-16 09:33:43
 */
public class MaximumNumberOfPairsInArray {
  public static void main(String[] args) {
    Solution solution = new MaximumNumberOfPairsInArray().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 哈希表
    public int[] numberOfPairs(int[] nums) {
      int[] freq = new int[101];
      for (int x : nums) {
        freq[x]++;
      }
      int leftover = Arrays.stream(freq).map(x -> x % 2).sum();
      return new int[]{(nums.length - leftover) / 2, leftover};
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}