// 存在一个 <strong>无向图</strong> ，图中有 <code>n</code> 个节点。其中每个节点都有一个介于 <code>0</code> 到 <code>n - 1</code> 之间的唯一编号。给你一个二维数组 <code>graph</code> ，其中 <code>graph[u]</code> 是一个节点数组，由节点 <code>u</code> 的邻接节点组成。形式上，对于 <code>graph[u]</code> 中的每个 <code>v</code> ，都存在一条位于节点 <code>u</code> 和节点 <code>v</code> 之间的无向边。该无向图同时具有以下属性：
//
//<ul> 
// <li>不存在自环（<code>graph[u]</code> 不包含 <code>u</code>）。</li> 
// <li>不存在平行边（<code>graph[u]</code> 不包含重复值）。</li> 
// <li>如果 <code>v</code> 在 <code>graph[u]</code> 内，那么 <code>u</code> 也应该在 <code>graph[v]</code> 内（该图是无向图）</li> 
// <li>这个图可能不是连通图，也就是说两个节点 <code>u</code> 和 <code>v</code> 之间可能不存在一条连通彼此的路径。</li> 
//</ul>
//
//<p><strong>二分图</strong> 定义：如果能将一个图的节点集合分割成两个独立的子集 <code>A</code> 和 <code>B</code> ，并使图中的每一条边的两个节点一个来自 <code>A</code> 集合，一个来自 <code>B</code> 集合，就将这个图称为 <strong>二分图</strong> 。</p>
//
//<p>如果图是二分图，返回 <code>true</code><em> </em>；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/21/bi2.jpg" style="width: 222px; height: 222px;" /> 
//<pre>
//<strong>输入：</strong>graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
//<strong>输出：</strong>false
//<strong>解释：</strong><span><code>不能将节点分割成两个独立的子集，</code></span>以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/21/bi1.jpg" style="width: 222px; height: 222px;" /> 
//<pre>
//<strong>输入：</strong>graph = [[1,3],[0,2],[1,3],[0,2]]
//<strong>输出：</strong>true
//<strong>解释：</strong><span><code>可以将节点分成两组: {0, 2} 和 {1, 3} 。</code></span></pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>graph.length == n</code></li> 
// <li><code>1 &lt;= n &lt;= 100</code></li> 
// <li><code>0 &lt;= graph[u].length &lt; n</code></li> 
// <li><code>0 &lt;= graph[u][i] &lt;= n - 1</code></li> 
// <li><code>graph[u]</code> 不会包含 <code>u</code></li> 
// <li><code>graph[u]</code> 的所有值 <strong>互不相同</strong></li> 
// <li>如果 <code>graph[u]</code> 包含 <code>v</code>，那么 <code>graph[v]</code> 也会包含 <code>u</code></li> 
//</ul>
//
//<div><li>👍 435</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 785.判断二分图
// 开题时间：2023-01-14 12:39:09
public class IsGraphBipartite {
  public static void main(String[] args) {
    Solution solution = new IsGraphBipartite().new Solution();
    System.out.println(solution.isBipartite(Tools.to2DIntArray("[[3],[2,4],[1],[0,4],[1,3]]")));
    //        System.out.println(solution.isBipartite(Tools.to2DIntArray("[[1,3],[0,2],[1,3],[0,2]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean isBipartiteX(int[][] graph) {
      List<Set<Integer>[]> list = new ArrayList<>();
      Map<Integer, int[]> node2idx = new HashMap<>();
      
      for (int i = 0; i < graph.length; i++) {
        int[] idx = null;
        if ((idx = node2idx.get(i)) == null)
          for (int j : graph[i])
            if ((idx = node2idx.get(j)) != null)
              break;
        
        if (idx == null) {
          int finalI = i;
          list.add(new Set[]{new HashSet<Integer>() {{
            add(finalI);
          }}, new HashSet<>(Arrays.stream(graph[i]).boxed().toList())});
          node2idx.put(finalI, new int[]{list.size() - 1, 0});
          for (int j : graph[i])
            node2idx.put(j, new int[]{list.size() - 1, 1});
        } else {
          if (idx == node2idx.get(i)) {
            Set<Integer> set = list.get(idx[0])[idx[1] ^ 1];
            for (int j : graph[i])
              if (set.add(j)) {
                if (node2idx.containsKey(j))
                  return false;
                node2idx.put(j, new int[]{idx[0], idx[1] ^ 1});
              }
          } else {
            if (list.get(idx[0])[idx[1] ^ 1].add(i)) {
              if (node2idx.containsKey(i))
                return false;
              node2idx.put(i, new int[]{idx[0], idx[1] ^ 1});
            }
            Set<Integer> set = list.get(idx[0])[idx[1]];
            for (int j : graph[i])
              if (set.add(j)) {
                if (node2idx.containsKey(j))
                  return false;
                node2idx.put(j, new int[]{idx[0], idx[1]});
              }
          }
        }
      }
      
      return true;
    }
    
    public boolean isBipartiteXX(int[][] graph) {
      int n = graph.length;
      int[] color = new int[n];
      Arrays.fill(color, -1);
      for (int i = 0; i < n; i++) {
        for (int j : graph[i]) {
          if (color[i] != -1 && color[i] == color[j])
            return false;
          else if (color[i] == -1 && color[j] == -1) {
            color[i] = 0;
            color[j] = 1;
          } else if (color[i] == -1) {
            color[i] = 1 ^ color[j];
          } else if (color[j] == -1) {
            color[j] = 1 ^ color[i];
          }
        }
      }
      return true;
    }
    
    //☆☆☆☆☆ dfs + 染色
    public boolean isBipartite9(int[][] graph) {
      int n = graph.length;
      int[] colors = new int[n];
      for (int i = 0; i < n; i++)
        if (colors[i] == 0 && !dfs(graph, colors, i, 1))
          return false;
      return true;
    }
    
    private boolean dfs(int[][] graph, int[] colors, int i, int color) {
      if (colors[i] != 0)
        return colors[i] == color;
      
      colors[i] = color;
      for (int j : graph[i])
        if (!dfs(graph, colors, j, -color))
          return false;
      
      return true;
    }
    
    // bfs + 染色
    public boolean isBipartite8(int[][] graph) {
      int n = graph.length;
      int[] colors = new int[n];
      Queue<Integer> q = new LinkedList<>();
      for (int i = 0; i < n; i++)
        if (colors[i] == 0) {
          q.offer(i);
          colors[i] = 1;
          while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
              if (colors[u] == colors[v])
                return false;
              if (colors[v] == 0) {
                colors[v] = -colors[u];
                q.offer(v);
              }
            }
          }
        }
      return true;
    }
    
    /*
     * 并查集
     *  原理：每个顶点的所有邻接点属于同一集合，且不与顶点处于同一集合
     *  实操：将当前顶点的所有邻接点进行合并，某一邻接点已经和当前顶点处于同一个集合中了，则不是二分图。
     */
    public boolean isBipartite(int[][] graph) {
      int n = graph.length;
      UnionFind uf = new UnionFind(n);
      for (int u = 0; u < n; u++) {
        for (int v : graph[u]) {
          if (uf.connected(u, v)) {
            return false;
          }
          uf.union(graph[u][0], v);
        }
      }
      return true;
    }
  }
  
  public class UnionFind {
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