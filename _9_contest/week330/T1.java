package org.example.leetcode.problems._9_contest.week330;

import java.util.HashSet;
import java.util.Set;

//
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution.distinctIntegers(5));
  }
  
  class Solution {
    boolean[] vis;
    
    public int distinctIntegers(int n) {
      Set<Integer> set = new HashSet<>();
      vis = new boolean[n + 1];
      vis[n] = true;
      set.add(n);
      return dfs(set);
    }
    
    public int dfs(Set<Integer> set) {
      int res = set.size();
      Set<Integer> next = new HashSet<>();
      for (int x : set) {
        for (int i = 1; i <= x; i++) {
          if (x % i == 1 && !vis[i]) {
            next.add(i);
            vis[i]=true;
          }
        }
      }
      if (!next.isEmpty()) {
        res += dfs(next);
      }
      return res;
    }
  }
}
