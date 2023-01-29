package org.example.leetcode.problems._9_contest.week330;

import java.util.HashSet;
import java.util.Set;

// 6337. Count Distinct Numbers on Board
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution.distinctIntegers(5));
  }
  
  class Solution {
    boolean[] vis;
    
    // BFS
    public int distinctIntegers9(int n) {
      Set<Integer> set = new HashSet<>();
      vis = new boolean[n + 1];
      vis[n] = true;
      set.add(n);
      return bfs(set);
    }
    
    public int bfs(Set<Integer> set) {
      int res = set.size();
      Set<Integer> next = new HashSet<>();
      for (int x : set) {
        for (int i = 1; i <= x; i++) {
          if (x % i == 1 && !vis[i]) {
            next.add(i);
            vis[i] = true;
          }
        }
      }
      if (!next.isEmpty()) {
        res += bfs(next);
      }
      return res;
    }
  
    // DFS
    public int distinctIntegers8(int n) {
      vis = new boolean[n + 1];
      vis[n] = true;
      
      dfs(n);
      
      int ans = 0;
      for (int i = 1; i <= n; i++) {
        if (vis[i]) {
          ans++;
        }
      }
      return ans;
    }
    
    public void dfs(int n) {
      for (int i = 2; i < n; i++) {
        if (n % i == 1 && !vis[i]) {
          vis[i] = true;
          dfs(i);
        }
      }
    }
    
    /*
     * ☆☆☆☆☆ 数学（脑筋急转弯）
     * 由于 n−1 一定满足要求，不断循环后，[2,n] 都会在桌面上，答案为 n−1。
     * 注意特判 n=1 的情况，此时答案为 1。
     */
    public int distinctIntegers(int n) {
      return n == 1 ? 1 : n - 1;
    }
  }
}
