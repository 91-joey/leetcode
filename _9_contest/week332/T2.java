package org.example.leetcode.problems._9_contest.week332;

import java.util.Map;
import java.util.TreeMap;

//
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
    
    public long countFairPairs(int[] nums, int lower, int upper) {
      long ans = 0;
      TreeMap<Integer, Integer> map = new TreeMap<>();
      for (int x : nums) {
        map.merge(x, 1, Integer::sum);
      }
      
      int preCnt = 0;
      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        int x = entry.getKey();
        int y = entry.getValue() + preCnt;
        preCnt = y;
        map.put(x, y);
      }
      
      for (int x : nums) {
        Map.Entry<Integer, Integer> ceilingEntry = map.floorEntry(upper - x);
        Map.Entry<Integer, Integer> floorEntry = map.floorEntry(lower - x - 1);
        Integer pre = floorEntry != null ? floorEntry.getValue() : 0;
        Integer suf = ceilingEntry != null ? ceilingEntry.getValue() : pre;
        ans += suf - pre;
        if (lower <= 2 * x && 2 * x <= upper) {
          ans--;
        }
      }
      
      return ans / 2;
    }
  }
}
