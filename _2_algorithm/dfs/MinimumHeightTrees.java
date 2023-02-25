package _2_algorithm.dfs;

import _3_common.tool.Tools;

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
    ArrayList<Integer> ans = new ArrayList<>();
    ArrayList<Integer>[] graph;
    int[] f1;
    int[] f2;
    int[] g;
    
    // 枚举每个顶点作为根节点，求其高度
    public List<Integer> findMinHeightTreesTLE(int n, int[][] edges) {
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
    
    // bfs（类似拓扑排序）
    public List<Integer> findMinHeightTrees9(int n, int[][] edges) {
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
          for (Integer v : g[q.poll()]) {
            if (--deg[v] == 1) {
              q.offer(v);
            }
          }
        }
      }
      
      return ans;
    }
    
    // （优化）bfs（类似拓扑排序）    重要结论：所求根节点为 1 个 或 2 个
    public List<Integer> findMinHeightTrees8(int n, int[][] edges) {
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
      }
      
      return q;
    }
    
    // ☆☆☆☆☆ 树形dp
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
      graph = new ArrayList[n];
      Arrays.setAll(graph, i -> new ArrayList<>());
      for (int[] edge : edges) {
        graph[edge[0]].add(edge[1]);
        graph[edge[1]].add(edge[0]);
      }
      
      // 状态定义：当前节点（包括当前节点）到叶子节点的最大路径长度，按方向（父节点方向↑、子树方向↓）划分
      f1 = new int[n]; // 子树方向↓ 的最大值
      f2 = new int[n]; // 子树方向↓ 的次大值（用于父节点方向↑ 的最大值的计算）
      g = new int[n]; // 父节点方向↑ 的最大值
      
      // 状态转移：分别进行两次 dfs，先下↓再上↑
      dfsDown(0, -1);
      dfsUp(0, -1);
      
      for (int i = 0, min = n - 1; i < n; i++) {
        // 当前节点（包括当前节点）到叶子节点的最大路径长度 = max （父节点方向↑，子树方向↓）
        int cur = Math.max(f1[i], g[i]);
        if (cur < min) {
          min = cur;
          ans.clear();
          ans.add(i);
        } else if (cur == min) {
          ans.add(i);
        }
      }
      
      return ans;
    }
    
    private int dfsDown(int u, int fa) {
      for (Integer v : graph[u]) {
        if (v == fa) {
          continue;
        }
        int sub = dfsDown(v, u) + 1;
        // 更新最大次大值
        if (sub > f1[u]) {
          f2[u] = f1[u];
          f1[u] = sub;
        } else if (sub > f2[u]) {
          f2[u] = sub;
        }
      }
      return f1[u];
    }
    
    private void dfsUp(int u, int fa) {
      for (Integer v : graph[u]) {
        if (v == fa) {
          continue;
        }
        // 小技巧：为避免对 fa 节点为空的处理，将「用 fa 来更新 u」调整为「用 u 来更新 v」。
        g[v] = Math.max(g[v], 1 + g[u]); // ① 父节点的「父节点方向」最大值 + 1
        g[v] = Math.max(g[v], 1 +
            (f1[v] + 1 == f1[u] ?
                f2[u] : // ② 父节点的「子树节点方向」次大值 + 1 （父节点的「子树节点方向」最长路径经过当前节点 v）
                f1[u] // ③ 父节点的「子树节点方向」最大值 + 1 （父节点的「子树节点方向」最长路径不经过当前节点 v）
            ));
        
        dfsUp(v, u);
      }
    }
    
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