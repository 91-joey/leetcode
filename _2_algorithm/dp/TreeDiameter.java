package _2_algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 1245.树的直径 <br>
 * 开题时间：2023-02-17 17:36:15
 */
public class TreeDiameter {
  public static void main(String[] args) {
    Solution solution = new TreeDiameter().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 暴力枚举所有叶子节点，求出最长路径
    public int treeDiameter9(int[][] edges) {
      int n = edges.length + 1;
      int[] deg = new int[n];
      for (int[] edge : edges) {
        deg[edge[0]]++;
        deg[edge[1]]++;
      }
      
      ArrayList<Integer>[] g = new ArrayList[n];
      Arrays.setAll(g, i -> new ArrayList<>());
      for (int[] edge : edges) {
        g[edge[0]].add(edge[1]);
        g[edge[1]].add(edge[0]);
      }
      
      int ans = 0;
      for (int i = 0; i < n; i++) {
        if (deg[i] == 1) {
          ans = Math.max(ans, maxDepth(g, i, new boolean[n]));
        }
      }
      
      return Math.max(ans - 1, 0);
    }
    
    private int maxDepth(ArrayList<Integer>[] g, int i, boolean[] vis) {
      vis[i] = true;
      int ans = 0;
      for (Integer j : g[i]) {
        if (!vis[j]) {
          ans = Math.max(ans, maxDepth(g, j, vis));
        }
      }
      return ans + 1;
    }
  
    // 拓扑排序
    public int treeDiameter8(int[][] edges) {
      int n = edges.length + 1;
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
    
      int ans = 0;
      int remainNodes = n;
      while (remainNodes > 2) {
        int size = q.size();
        remainNodes -= size;
        for (int i = 0; i < size; i++) {
          for (Integer v : g[q.poll()]) {
            if (--deg[v] == 1) {
              q.offer(v);
            }
          }
        }
        ans += 2;
      }
    
      return ans + remainNodes - 1;
    }
  
    ArrayList<Integer>[] g;
    int ans = 0;
    
    // ☆☆☆☆☆ 树形DP
    public int treeDiameter(int[][] edges) {
      int n = edges.length + 1;
      this.g = new ArrayList[n];
      Arrays.setAll(g, i -> new ArrayList<>());
      for (int[] edge : edges) {
        g[edge[0]].add(edge[1]);
        g[edge[1]].add(edge[0]);
      }
      
      dfs(0, -1);
      
      return ans;
    }
    
    /**
     * 求以 u 为根节点、以 fa 为父节点（不能返回父节点）的最大路径长度
     *
     * @param u  根节点
     * @param fa 父节点
     */
    private int dfs(int u, int fa) {
      int max1 = 0; // 最大值
      int max2 = 0; // 次大值
      
      for (Integer v : g[u]) {
        if (v == fa) {
          continue;
        }
        
        int depth = dfs(v, u) + 1;
        if (depth > max1) {
          max2 = max1;
          max1 = depth;
        } else if (depth > max2) {
          max2 = depth;
        }
      }
      
      // 经过 u 的最长链的长度 = 以 u 为根节点的最大路径长度 +　以 u 为根节点的次大路径长度
      ans = Math.max(ans, max1 + max2);
      
      return max1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}