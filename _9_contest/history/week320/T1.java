package org.example.leetcode.problems._9_contest.history.week320;

import java.util.Arrays;

// 6241. Number of Unequal Triplets in Array
public class T1 {
  public static void main(String[] args) {
  
  }
  
  // 暴力
  public int unequalTriplets9(int[] nums) {
    int cnt = 0;
    for (int i = 0; i < nums.length - 2; i++) {
      for (int j = i + 1; j < nums.length - 1; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          if (nums[i] != nums[j] && nums[i] != nums[k] && nums[j] != nums[k])
            cnt++;
        }
      }
    }
    return cnt;
  }
  
  // 排序 + 遍历
  // 当前元素值所能组成的三元组个数 = 相同元素值的个数 * 小于元素值的个数 * 大于元素值的个数
  public int unequalTriplets(int[] nums) {
    int cnt = 0;
    Arrays.sort(nums);
    
    int n = nums.length;
    for (int l = 0, r = 1; r < n; ) {
      if (nums[l] != nums[r]) {
        cnt += (r - l) * l * (n - r);
        l = r;
      } else {
        r++;
      }
    }
    return cnt;
  }
}
