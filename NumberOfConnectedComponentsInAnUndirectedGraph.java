package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 323.无向图中连通分量的数目 <br>
 * 开题时间：2023-02-04 18:02:04
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
  public static void main(String[] args) {
    Solution solution = new NumberOfConnectedComponentsInAnUndirectedGraph().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // dfs
    public int countComponents9(int n, int[][] edges) {
      ArrayList<Integer>[] g = new ArrayList[n];
      Arrays.setAll(g, i -> new ArrayList<>());
      for (int[] edge : edges) {
        g[edge[0]].add(edge[1]);
        g[edge[1]].add(edge[0]);
      }
      
      int ans = 0;
      boolean[] visited = new boolean[n];
      for (int i = 0; i < n; i++) {
        if (!visited[i]) {
          ans++;
          dfs(g, visited, i);
        }
      }
      return ans;
    }
    
    private void dfs(ArrayList<Integer>[] g, boolean[] visited, int i) {
      visited[i] = true;
      for (int j : g[i]) {
        if (!visited[j]) {
          dfs(g, visited, j);
        }
      }
    }
    
    // Union Find
    public int countComponents(int n, int[][] edges) {
      UnionFind uf = new UnionFind(n);
      
      for (int[] edge : edges) {
        uf.union(edge[0], edge[1]);
      }
      
      return uf.components;
    }
  }
  
  class UnionFind {
    int[] root;
    int[] rank;
    int components;
    
    public UnionFind(int size) {
      root = new int[size];
      rank = new int[size];
      for (int i = 0; i < size; i++)
        root[i] = i;
      components = size;
    }
    
    public int find(int x) {
      if (x == root[x])
        return x;
      return root[x] = find(root[x]);
    }
    
    public void union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX != rootY) {
        if (rank[rootX] == rank[rootY]) {
          root[rootX] = rootY;
          rank[rootY]++;
        } else if (rank[rootX] < rank[rootY])
          root[rootX] = rootY;
        else
          root[rootY] = rootX;
        components--;
      }
    }
    
    public boolean connected(int x, int y) {
      return find(x) == find(y);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}