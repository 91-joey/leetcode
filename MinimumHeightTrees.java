package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 310.最小高度树 <br>
 * 开题时间：2023-02-18 16:53:00
 */
public class MinimumHeightTrees {
  public static void main(String[] args) {
    Solution solution = new MinimumHeightTrees().new Solution();
    System.out.println(solution.findMinHeightTrees(4, Tools.to2DIntArray("[[1,0],[1,2],[1,3]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int min = Integer.MAX_VALUE;
    ArrayList<Integer> ans;
    
    public List<Integer> findMinHeightTreesTLE(int n, int[][] edges) {
      ans = new ArrayList<>();
      
      ArrayList<Integer>[] g = new ArrayList[n];
      Arrays.setAll(g, i -> new ArrayList<>());
      for (int[] edge : edges) {
        g[edge[0]].add(edge[1]);
        g[edge[1]].add(edge[0]);
      }
      
      for (int i = 0; i < n; i++) {
        int max = dfs(g, i, -1);
        
        if (max < min) {
          min = max;
          ans.clear();
          ans.add(i);
        } else if (max == min) {
          ans.add(i);
        }
      }
      
      return ans;
    }
    
    // bfs
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
      ArrayList<Integer>[] g = new ArrayList[n];
      int[] deg = new int[n];
      Arrays.setAll(g, i -> new ArrayList<>());
      for (int[] edge : edges) {
        g[edge[0]].add(edge[1]);
        g[edge[1]].add(edge[0]);
        deg[edge[0]]++;
        deg[edge[1]]++;
      }
      
      LinkedList<Integer> q = new LinkedList<>();
      for (int i = 0; i < n; i++) {
        if (deg[i] <= 1) {
          q.offer(i);
        }
      }
      
      while (!q.isEmpty()) {
        ans = new ArrayList<>(q);
        int size = q.size();
        for (int i = 0; i < size; i++) {
          int u = q.poll();
          for (Integer v : g[u]) {
            if (--deg[v] == 1) {
              q.offer(v);
            }
          }
        }
      }
      
      return ans;
    }
    
    // todo 树形dp
    
    private int dfs(ArrayList<Integer>[] g, int u, int fa) {
      int max = 0;
      for (Integer v : g[u]) {
        if (v == fa)
          continue;
        int val = dfs(g, v, u);
        if (val > max) {
          max = val;
        }
      }
      
      return max + 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}