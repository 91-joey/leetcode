package org.example.leetcode.problems._1_dataStructure.monotonic;

import java.util.HashMap;

/**
 * 1124.表现良好的最长时间段 <br>
 * 开题时间：2023-02-14 08:50:21
 */
public class LongestWellPerformingInterval {
  public static void main(String[] args) {
    Solution solution = new LongestWellPerformingInterval().new Solution();
    System.out.println(solution.longestWPI(new int[]{9, 9, 6, 0, 6, 6, 9}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // brute force
    public int longestWPI9(int[] hours) {
      int ans = 0;
      int n = hours.length;
      for (int i = 0; i < n - ans; i++) {
        for (int j = i, sum = 0; j < n; j++) {
          if ((sum += 8 < hours[j] ? 1 : -1) > 0) {
            ans = Math.max(ans, j - i + 1);
          }
        }
      }
      return ans;
    }
    
    // 前缀和 + 哈希表
    public int longestWPI(int[] hours) {
      int ans = 0;
      HashMap<Integer, Integer> val2firstIdx = new HashMap<>();
      for (int i = 0, sum = 0; i < hours.length; i++) {
        sum += 8 < hours[i] ? 1 : -1;
        ans = Math.max(ans, sum > 0 ?
            i + 1 :
            i - val2firstIdx.getOrDefault(sum - 1, i));
        val2firstIdx.putIfAbsent(sum, i);
      }
      return ans;
    }
    
    // todo 单调栈
  }
  // leetcode submit region end(Prohibit modification and deletion)
}