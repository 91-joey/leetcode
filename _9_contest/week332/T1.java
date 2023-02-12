package org.example.leetcode.problems._9_contest.week332;

// 6354. Find the Array Concatenation Value
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution.findTheArrayConcVal(new int[]{5, 14, 13, 8, 12}));
  }
  
  class Solution {
    // 字符串拼接和解析
    public long findTheArrayConcVal9(int[] nums) {
      long ans = 0;
      for (int l = 0, r = nums.length - 1; l <= r; l++, r--) {
        ans += l == r ?
            nums[l] :
            Long.parseLong("" + nums[l] + nums[r]);
      }
      return ans;
    }
    
    // 数位运算
    public long findTheArrayConcVal(int[] nums) {
      long ans = 0;
      
      int l = 0;
      int r = nums.length - 1;
      while (l < r) {
        int x = nums[l++];
        int y = nums[r];
        while (y > 0) {
          x *= 10;
          y /= 10;
        }
        ans += x + nums[r--];
      }
      
      if (l == r) {
        ans += nums[l];
      }
      return ans;
    }
  }
}
