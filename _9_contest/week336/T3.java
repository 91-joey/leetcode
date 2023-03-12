package _9_contest.week336;

import java.util.HashMap;

//
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    
    public long beautifulSubarrays(int[] nums) {
      long ans = 0;
      HashMap<Integer, Integer> map = new HashMap<>();
      int s = 0;
      map.put(0, 1);
      for (int x : nums) {
        s ^= x;
        ans += map.getOrDefault(s, 0);
        map.merge(s, 1, Integer::sum);
      }
      return ans;
    }
  }
}
