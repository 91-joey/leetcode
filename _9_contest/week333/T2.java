package org.example.leetcode.problems._9_contest.week333;

//
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution.minOperations(27));
    System.out.println(Integer.toBinaryString(27));
    System.out.println(solution.minOperations(39));
    System.out.println(Integer.toBinaryString(39));
    System.out.println(solution.minOperations(54));
    System.out.println(Integer.toBinaryString(54));
  }
  
  class Solution {
    
    int ans;
    
    public int minOperations9(int n) {
      int cnt = Integer.bitCount(n);
      int leadingZeros = Integer.numberOfLeadingZeros(n);
      return Math.min(cnt, 32 - leadingZeros - cnt + 2);
    }
    
    public int minOperations(int n) {
      ans = 32;
      int cnt = Integer.bitCount(n);
      int leadingZeros = Integer.numberOfLeadingZeros(n);
      
      dfs(n, 0, 0, 32 - leadingZeros);
      
      return ans;
    }
    
    private void dfs(int n, int tier, int cnt, int bound) {
      if (ans < cnt) {
        return;
      }
      if (tier == bound + 1) {
        if (n == 0) {
          ans = Math.min(ans, cnt);
        }
        return;
      }
  
      dfs(n, tier + 1, cnt, bound);
      dfs(n + (1 << tier), tier + 1, cnt + 1, bound);
      dfs(n - (1 << tier), tier + 1, cnt + 1, bound);
    }
  }
}
