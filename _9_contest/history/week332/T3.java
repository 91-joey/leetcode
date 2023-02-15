package org.example.leetcode.problems._9_contest.history.week332;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;
import java.util.HashMap;

// 6356. Substring XOR Queries
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    System.out.println(Arrays.deepToString(solution.substringXorQueries("101101", Tools.to2DIntArray("[[0,5],[1,2]]"))));
  }
  
  class Solution {
    public static final int[] NOT_FOUND = {-1, -1};
    
    // 哈希表
    public int[][] substringXorQueries9(String s, int[][] queries) {
      HashMap<Integer, int[]> map = new HashMap<>();
      for (int i = s.length() - 1; i >= 0; i--) {
        for (int len = 1; len < 32 && i + len <= s.length(); len++) {
          int key = Integer.parseInt(s.substring(i, i + len), 2);
          if (map.containsKey(key)) {
            int[] val = map.getOrDefault(key, new int[]{-1, -1});
            if (val[1] - val[0] + 1 >= len) {
              map.put(key, new int[]{i, i + len - 1});
            }
          } else {
            map.put(key, new int[]{i, i + len - 1});
          }
        }
      }
      
      int n = queries.length;
      int[][] ans = new int[n][2];
      for (int i = 0; i < n; i++) {
        ans[i] = map.getOrDefault(queries[i][0] ^ queries[i][1], new int[]{-1, -1});
      }
      
      return ans;
    }
    
    // 哈希表（优化）
    public int[][] substringXorQueries(String s, int[][] queries) {
      HashMap<Integer, int[]> map = new HashMap<>();
      int n = s.length();
      for (int i = 0; i < n; i++) {
        int bound = Math.min(n, i + 30);
        for (int j = i; j < bound; j++) {
          int key = Integer.parseInt(s.substring(i, j + 1), 2);
          int[] val = map.get(key);
          if (val == null || j - i < val[1] - val[0]) {
            map.put(key, new int[]{i, j});
          }
        }
      }
      
      int m = queries.length;
      int[][] ans = new int[m][];
      for (int i = 0; i < m; i++) {
        ans[i] = map.getOrDefault(queries[i][0] ^ queries[i][1], NOT_FOUND);
      }
      
      return ans;
    }
  }
}
