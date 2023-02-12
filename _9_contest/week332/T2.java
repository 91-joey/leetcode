package org.example.leetcode.problems._9_contest.week332;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

// 6355. Count the Number of Fair Pairs
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution.countFairPairs(new int[]{0, 0, 0, 0, 0, 0,}, 0, 0));
    // System.out.println(solution.countFairPairs(new int[]{0, 1, 7, 4, 4, 5}, 3, 6));
  }
  
  class Solution {
    public long countFairPairsTLE(int[] nums, int lower, int upper) {
      long ans = 0;
      TreeMap<Integer, Integer> set = new TreeMap<>();
      for (int x : nums) {
        for (Map.Entry<Integer, Integer> entry : set.subMap(lower - x, upper - x + 1).entrySet()) {
          Integer k = entry.getKey();
          Integer v = entry.getValue();
          ans += v;
        }
        set.merge(x, 1, Integer::sum);
      }
      return ans;
    }
    
    /*
     * 有序映射 + 前缀和
     */
    public long countFairPairs9(int[] nums, int lower, int upper) {
      long ans = 0;
      TreeMap<Integer, Integer> map = new TreeMap<>();
      for (int x : nums) {
        // 计数
        map.merge(x, 1, Integer::sum);
      }
      
      // 有序映射中的前缀和（<=value 的个数）
      int preCnt = 0;
      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        int x = entry.getKey();
        int y = entry.getValue() + preCnt;
        preCnt = y;
        map.put(x, y);
      }
      
      // 查找相配元素值的上下界
      for (int x : nums) {
        Map.Entry<Integer, Integer> ceilingEntry = map.floorEntry(upper - x);
        Map.Entry<Integer, Integer> floorEntry = map.floorEntry(lower - x - 1);
        Integer pre = floorEntry != null ? floorEntry.getValue() : 0;
        Integer suf = ceilingEntry != null ? ceilingEntry.getValue() : 0;
        ans += suf - pre;
        // 不能与自身配对
        if (lower <= 2 * x && 2 * x <= upper) {
          ans--;
        }
      }
      
      // 两个元素只算一对
      return ans / 2;
    }
    
    
    // 二分
    public long countFairPairs8(int[] nums, int lower, int upper) {
      long ans = 0;
      int n = nums.length;
      Arrays.sort(nums);
      
      for (int x : nums) {
        int l = binarySearch(nums, 0, n, lower - x);
        int r = binarySearch(nums, l, n, upper - x + 1);
        ans += r - l - (lower <= 2 * x && 2 * x <= upper ? 1 : 0);
      }
      
      return ans / 2;
    }
    
    // ☆☆☆☆☆ 二分（优化）
    public long countFairPairs(int[] nums, int lower, int upper) {
      long ans = 0;
      int n = nums.length;
      Arrays.sort(nums);
      
      for (int i = 1; i < n; i++) {
        int l = binarySearch(nums, 0, i, lower - nums[i]);
        int r = binarySearch(nums, l, i, upper - nums[i] + 1);
        ans += r - l;
      }
      
      return ans;
    }
    
    public static int binarySearch(int[] arr, int l, int r, int target) {
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if (target <= arr[mid])
          r = mid;
        else
          l = mid + 1;
      }
      return r;
    }
    
  }
}
