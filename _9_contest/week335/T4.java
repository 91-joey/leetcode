package _9_contest.week335;

//
public class T4 {
  public static void main(String[] args) {
    Solution solution = new T4().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
  
    public int waysToReachTarget(int target, int[][] types) {
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
  }
}
