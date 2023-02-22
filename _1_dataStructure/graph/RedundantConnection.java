package _1_dataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 684.冗余连接 <br>
 * 开题时间：2023-02-05 08:36:45
 */
public class RedundantConnection {
  public static void main(String[] args) {
    Solution solution = new RedundantConnection().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // Union Find
    public int[] findRedundantConnection9(int[][] edges) {
      UnionFind uf = new UnionFind(edges.length + 1);
      for (int[] edge : edges) {
        if (uf.connected(edge[0], edge[1])) {
          return edge;
        }
        uf.union(edge[0], edge[1]);
      }
      return new int[2];
    }
    
    public int[] findRedundantConnectionX(int[][] edges) {
      int n = edges.length;
      ArrayList<Integer>[] g = new ArrayList[n + 1];
      Arrays.setAll(g, i -> new ArrayList<>());
      for (int[] edge : edges) {
        g[edge[0]].add(edge[1]);
        g[edge[1]].add(edge[0]);
      }
      
      return dfs(g, new boolean[n + 1], 1);
    }
    
    private int[] dfs(ArrayList<Integer>[] g, boolean[] vis, int i) {
      vis[i] = true;
      for (Integer j : g[i]) {
        if (vis[j]) {
          return new int[]{i, j};
        }
        int[] ans = dfs(g, vis, j);
        if (ans != null) {
          return ans;
        }
      }
      return null;
    }
  }
  
  class UnionFind {
    int[] root;
    int[] rank;
    
    public UnionFind(int size) {
      root = new int[size];
      rank = new int[size];
      for (int i = 0; i < size; i++)
        root[i] = i;
    }
    
    public int find(int x) {
      if (x == root[x])
        return x;
      return root[x] = find(root[x]);
    }
    
    public void union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX != rootY)
        if (rank[rootX] == rank[rootY]) {
          root[rootX] = rootY;
          rank[rootY]++;
        } else if (rank[rootX] < rank[rootY])
          root[rootX] = rootY;
        else
          root[rootY] = rootX;
    }
    
    public boolean connected(int x, int y) {
      return find(x) == find(y);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}