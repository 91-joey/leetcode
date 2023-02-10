package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;

/**
 * 473.火柴拼正方形 <br>
 * 开题时间：2023-02-10 17:13:21
 */
public class MatchsticksToSquare {
  public static void main(String[] args) {
    Solution solution = new MatchsticksToSquare().new Solution();
    System.out.println(solution.makesquare(new int[]{5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3}));
    // System.out.println(Tools.factorial(15));
    long comb = Tools.comb(16, 4);
    System.out.println(comb * comb * comb);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int edge;
    boolean[] vis;
    
    public boolean makesquare(int[] matchsticks) {
      Arrays.sort(matchsticks);
      
      int n = matchsticks.length;
      int sum = Arrays.stream(matchsticks).sum();
      edge = sum / 4;
      vis = new boolean[n];
      if (sum % 4 != 0 || edge < matchsticks[n - 1]) {
        return false;
      }
      
      return backtrack(matchsticks, 0, edge, sum);
    }
    
    private boolean backtrack(int[] matchsticks, int idx, int t, int remainder) {
      if (idx == 3) {
        if (remainder == edge) {
          return true;
        }
      } else if (t == 0) {
        return backtrack(matchsticks, idx + 1, edge, remainder);
      } else {
        int pre = Integer.MIN_VALUE;
        for (int i = 0; i < matchsticks.length && matchsticks[i] <= t; i++) {
          if (!vis[i] && matchsticks[i] != pre) {
            pre = matchsticks[i];
            vis[i] = true;
            if (backtrack(matchsticks, idx, t - matchsticks[i], remainder - matchsticks[i])) {
              return true;
            }
            vis[i] = false;
          }
        }
      }
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}