package org.example.leetcode.problems._9_contest.week330;

//
public class T4 {
  public static void main(String[] args) {
    Solution solution = new T4().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    public long countQuadruplets(int[] nums) {
      long ans = 0;
      int n = nums.length;
      for (int i = 0; i < n; i++) {
        for (int k = i + 2; k < n; k++) {
          if (nums[i] >= nums[k]) {
            continue;
          }
          for (int j = i + 1; j < k; j++) {
            if (nums[k] >= nums[j]) {
              continue;
            }
            for (int l = k + 1; l < n; l++) {
              if (nums[j] < nums[l]) {
                ans++;
              }
            }
          }
        }
      }
      return ans;
    }
  }
}
