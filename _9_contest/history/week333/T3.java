package org.example.leetcode.problems._9_contest.history.week333;

import java.util.HashMap;
import java.util.Map;

// 2572. Count the Number of Square-Free Subsets
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    System.out.println(solution.squareFreeSubsets(new int[]{8, 11, 17, 2, 25, 29, 21, 20, 4, 22
    }));
  }
  
  class Solution {
    public int squareFreeSubsetsX(int[] nums) {
      int ans = 0;
      HashMap<Integer, Integer> map = new HashMap<>();
      for (int x : nums) {
        if (containsNoSquare(x)) {
          int cnt = 1;
          for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            if (gcd(x, key) == 1) {
              cnt = (cnt + entry.getValue()) % 1000000007;
            }
          }
          map.put(x, (map.getOrDefault(x, 0) + cnt) % 1000000007);
          ans = (ans + cnt) % 1000000007;
        }
      }
      return ans;
    }
    
    // todo
    public int squareFreeSubsets(int[] nums) {
      return 0;
    }
    
    private boolean containsNoSquare(int x) {
      for (int i = 2; i < 6; i++) {
        if (x % (i * i) == 0) {
          return false;
        }
      }
      return true;
    }
    
    public static int gcd(int a, int b) {
      while (a != 0) {
        int tmp = a;
        a = b % a;
        b = tmp;
      }
      return b;
    }
    
  }
}
