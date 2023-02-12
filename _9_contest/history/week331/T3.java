package org.example.leetcode.problems._9_contest.history.week331;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

// 2560. House Robber IV
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // bs + dp
    public int minCapability(int[] nums, int k) {
      IntSummaryStatistics statistics = Arrays.stream(nums).summaryStatistics();
      int l = statistics.getMin();
      int r = statistics.getMax();
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if (k <= maxHouses(nums, mid))
          r = mid;
        else
          l = mid + 1;
      }
      return r;
    }
    
    private int maxHouses9(int[] nums, int bound) {
      int n = nums.length;
      int[] f = new int[n + 2];
      for (int i = 2; i < n + 2; i++) {
        f[i] = Math.max(f[i - 1], f[i - 2] + (nums[i - 2] <= bound ? 1 : 0));
      }
      return f[n + 1];
    }
    
    private int maxHouses(int[] nums, int bound) {
      int pre = 0;
      int cur = 0;
      for (int i = 0; i < nums.length; i++) {
        int tmp = cur;
        cur = Math.max(cur, pre + (nums[i] <= bound ? 1 : 0));
        pre = tmp;
      }
      return cur;
    }
  }
}
