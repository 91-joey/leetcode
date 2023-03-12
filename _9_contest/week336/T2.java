package _9_contest.week336;

import java.util.Arrays;

// 6316. Rearrange Array to Maximize Prefix Score
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
  
    // 排序 + 贪心
    public int maxScore(int[] nums) {
      Arrays.sort(nums);
      
      long prefix = 0; // ！！注意数据范围
      int n = nums.length;
      for (int i = n - 1; i >= 0; i--) {
        if ((prefix += nums[i]) <= 0) {
          return n - 1 - i;
        }
      }
      
      return n;
    }
  }
}
