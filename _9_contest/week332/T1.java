package org.example.leetcode.problems._9_contest.week332;

//
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    
    public long findTheArrayConcVal(int[] nums) {
      long ans = 0;
      for (int l = 0, r = nums.length - 1; l <= r; l++, r--) {
        String a = String.valueOf(nums[l]);
        String b = String.valueOf(nums[r]);
        ans += l == r ? Long.parseLong(b) : Long.parseLong(a + b);
      }
      return ans;
    }
  }
}
