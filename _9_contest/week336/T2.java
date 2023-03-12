package _9_contest.week336;

import java.util.Arrays;

//
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    
    public int maxScoreX(int[] nums) {
      // Sort the array in descending order
      Arrays.sort(nums);
      // reverse(nums);
      
      // Initialize prefix sum array and score
      // int[] prefix = new int[nums.length];
      int score = 0;
      
      // Calculate prefix sums and score
      for (int i = nums.length - 1, prefix = 0; i >= 0; i--) {
        // if (i == nums.length - 1) {
        //   prefix = nums[i];
        // } else {
        // }
        prefix = prefix + nums[i];
        if (prefix > 0) {
          score++;
        } else {
          break;
        }
      }
      
      // Return the maximum score
      return score;
    }
    
    public int maxScore(int[] nums) {
      Arrays.sort(nums);
      
      // int score = 0;
      
      long prefix = 0;
      for (int i = nums.length - 1; i >= 0; i--) {
        prefix += nums[i];
        // if (prefix > 0) {
        //   score++;
        // } else {
        //   break;
        // }
        if (prefix <= 0) {
          return nums.length - 1 - i;
        }
      }
      
      return  nums.length;
    }
  }
}
