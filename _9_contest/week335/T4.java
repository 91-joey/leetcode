package _9_contest.week335;

import _3_common.tool.Tools;

// 6310. Number of Ways to Earn Points
public class T4 {
  public static void main(String[] args) {
    Solution solution = new T4().new Solution();
    System.out.println(solution.waysToReachTarget(6, Tools.to2DIntArray("[[6,1],[3,2],[2,3]]")));
  }
  
  class Solution {
    // 分组背包（初版）
    public int waysToReachTarget9(int target, int[][] types) {
      // Modulo constant
      int MOD = 1000000007;
      // Number of types
      int n = types.length;
      // Initialize dp array
      int[][] f = new int[n + 1][target + 1];
      // Base case: one way to earn zero points
      f[0][0] = 1;
      // Loop through types
      for (int i = 1; i <= n; i++) {
        // Get count and marks for current type
        int count = types[i - 1][0];
        int marks = types[i - 1][1];
        // Loop through possible points
        for (int j = 0; j <= target; j++) {
          // Skip current type
          f[i][j] = f[i - 1][j];
          // Take current type k times
          for (int k = 1; k <= count && j - marks * k >= 0; k++) {
            // Add number of ways from previous type with remaining points
            f[i][j] += f[i - 1][j - marks * k];
            // Modulo operation
            f[i][j] %= MOD;
          }
        }
      }
      // Return final answer
      return f[n][target];
    }
    
    // 分组背包（优化）
    public int waysToReachTarget8(int target, int[][] types) {
      // Modulo constant
      int MOD = 1000000007;
      // Number of types
      int n = types.length;
      // Initialize dp array
      int[][] f = new int[n + 1][target + 1];
      // Base case: one way to earn zero points
      f[0][0] = 1;
      // Loop through types
      for (int i = 1; i <= n; i++) {
        // Get count and marks for current type
        int count = types[i - 1][0];
        int marks = types[i - 1][1];
        // Loop through possible points
        for (int j = 0; j <= target; j++) {
          // Take current type k times
          for (int k = Math.min(count, j / marks); k > -1; k--) {
            // Add number of ways from previous type with remaining points
            f[i][j] += f[i - 1][j - marks * k];
            // Modulo operation
            f[i][j] %= MOD;
          }
        }
      }
      // Return final answer
      return f[n][target];
    }
    
    // 分组背包（一维优化）
    public int waysToReachTarget(int target, int[][] types) {
      // Modulo constant
      int MOD = 1000000007;
      // Initialize dp array
      int[] f = new int[target + 1];
      // Base case: one way to earn zero points
      f[0] = 1;
      // Loop through types
      for (int[] type : types) {
        // Get count and marks for current type
        int count = type[0];
        int marks = type[1];
        // Loop through possible points
        for (int j = target; j > -1; j--) {
          // Take current type k times
          for (int k = Math.min(count, j / marks); k > 0; k--) {
            // Add number of ways from previous type with remaining points
            // Modulo operation
            f[j] = (f[j] + f[j - marks * k]) % MOD;
          }
        }
      }
      // Return final answer
      return f[target];
    }
  }
}
