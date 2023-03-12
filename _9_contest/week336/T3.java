package _9_contest.week336;

import java.util.HashMap;

// 6317. Count the Number of Beautiful Subarrays
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // 前缀和（异或） + 哈希表
    public long beautifulSubarrays9(int[] nums) {
      long ans = 0;
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, 1);
      int s = 0;
      for (int x : nums) {
        s ^= x;
        ans += map.getOrDefault(s, 0);
        map.merge(s, 1, Integer::sum);
      }
      return ans;
    }
  
    // （简化）前缀和（异或） + 哈希表
    public long beautifulSubarrays(int[] nums) {
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, 1);
      
      long ans = 0;
      int s = 0;
      for (int x : nums) {
        ans += map.merge((s ^= x), 1, Integer::sum) - 1;
      }
      
      return ans;
    }
  }
}
