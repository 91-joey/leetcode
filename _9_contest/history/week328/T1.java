package org.example.leetcode.problems._9_contest.history.week328;

// 6291. Difference Between Element Sum and Digit Sum of an Array
public class T1 {
  public static void main(String[] args) {
  
  }
  
  
  public int differenceOfSum9(int[] nums) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    int res = 0;
    for (int num : nums) {
      for (int i = num; i != 0; i /= 10) {
        int digit = i % 10;
        res += digit;
      }
    }
    return Math.abs(sum - res);
  }
  
  // 模拟（优化）
  public int differenceOfSum(int[] nums) {
    int ans = 0;
    for (int num : nums) {
      ans += num;
      for (; num != 0; num /= 10)
        ans -= num % 10;
    }
    return Math.abs(ans);
  }
}
