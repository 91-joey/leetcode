//<p>给定一组&nbsp;<code>n</code>&nbsp;人（编号为&nbsp;<code>1, 2, ..., n</code>），&nbsp;我们想把每个人分进<strong>任意</strong>大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。</p>
//
//<p>给定整数 <code>n</code>&nbsp;和数组 <code>dislikes</code>&nbsp;，其中&nbsp;<code>dislikes[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;，表示不允许将编号为 <code>a<sub>i</sub></code>&nbsp;和&nbsp;&nbsp;<code>b<sub>i</sub></code>的人归入同一组。当可以用这种方法将所有人分进两组时，返回 <code>true</code>；否则返回 <code>false</code>。</p>
//
//<p>&nbsp;</p>
//
//<ol> 
//</ol>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 4, dislikes = [[1,2],[1,3],[2,4]]
//<strong>输出：</strong>true
//<strong>解释：</strong>group1 [1,4], group2 [2,3]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3, dislikes = [[1,2],[1,3],[2,3]]
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 2000</code></li> 
// <li><code>0 &lt;= dislikes.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>dislikes[i].length == 2</code></li> 
// <li><code>1 &lt;= dislikes[i][j] &lt;= n</code></li> 
// <li><code>a<sub>i</sub>&nbsp;&lt; b<sub>i</sub></code></li> 
// <li><code>dislikes</code>&nbsp;中每一组都 <strong>不同</strong></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<div><li>👍 351</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 886.可能的二分法
// 开题时间：2023-01-14 19:56:24
public class PossibleBipartition {
  public static void main(String[] args) {
    Solution solution = new PossibleBipartition().new Solution();
    System.out.println(solution.possibleBipartition(50, Tools.to2DIntArray("[[21,47],[4,41],[2,41],[36,42],[32,45],[26,28],[32,44],[5,41],[29,44],[10,46],[1,6],[7,42],[46,49],[17,46],[32,35],[11,48],[37,48],[37,43],[8,41],[16,22],[41,43],[11,27],[22,44],[22,28],[18,37],[5,11],[18,46],[22,48],[1,17],[2,32],[21,37],[7,22],[23,41],[30,39],[6,41],[10,22],[36,41],[22,25],[1,12],[2,11],[45,46],[2,22],[1,38],[47,50],[11,15],[2,37],[1,43],[30,45],[4,32],[28,37],[1,21],[23,37],[5,37],[29,40],[6,42],[3,11],[40,42],[26,49],[41,50],[13,41],[20,47],[15,26],[47,49],[5,30],[4,42],[10,30],[6,29],[20,42],[4,37],[28,42],[1,16],[8,32],[16,29],[31,47],[15,47],[1,5],[7,37],[14,47],[30,48],[1,10],[26,43],[15,46],[42,45],[18,42],[25,42],[38,41],[32,39],[6,30],[29,33],[34,37],[26,38],[3,22],[18,47],[42,48],[22,49],[26,34],[22,36],[29,36],[11,25],[41,44],[6,46],[13,22],[11,16],[10,37],[42,43],[12,32],[1,48],[26,40],[22,50],[17,26],[4,22],[11,14],[26,39],[7,11],[23,26],[1,20],[32,33],[30,33],[1,25],[2,30],[2,46],[26,45],[47,48],[5,29],[3,37],[22,34],[20,22],[9,47],[1,4],[36,46],[30,49],[1,9],[3,26],[25,41],[14,29],[1,35],[23,42],[21,32],[24,46],[3,32],[9,42],[33,37],[7,30],[29,45],[27,30],[1,7],[33,42],[17,47],[12,47],[19,41],[3,42],[24,26],[20,29],[11,23],[22,40],[9,37],[31,32],[23,46],[11,38],[27,29],[17,37],[23,30],[14,42],[28,30],[29,31],[1,8],[1,36],[42,50],[21,41],[11,18],[39,41],[32,34],[6,37],[30,38],[21,46],[16,37],[22,24],[17,32],[23,29],[3,30],[8,30],[41,48],[1,39],[8,47],[30,44],[9,46],[22,45],[7,26],[35,42],[1,27],[17,30],[20,46],[18,29],[3,29],[4,30],[3,46]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 二分图：需要注意把两人的不喜欢关系看成无向边
    public boolean possibleBipartition9(int n, int[][] dislikes) {
      List<Integer>[] adjList = new List[++n];
      for (int i = 0; i < n; i++)
        adjList[i] = new ArrayList<>();
      for (int[] dislike : dislikes) {
        adjList[dislike[0]].add(dislike[1]);
        adjList[dislike[1]].add(dislike[0]);
      }
      
      int[][] graph = new int[n][];
      for (int i = 0; i < n; i++)
        graph[i] = adjList[i].stream().mapToInt(Integer::intValue).toArray();
      
      return isBipartite(graph);
    }
    
    //☆☆☆ dfs + 染色
    public boolean isBipartite(int[][] graph) {
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
  
    // ☆☆☆☆☆ 并查集
    public boolean possibleBipartition(int n, int[][] dislikes) {
      ArrayList<Integer>[] g = new ArrayList[n + 1];
      Arrays.setAll(g, i -> new ArrayList<>());
      for (int[] edge : dislikes) {
        g[edge[0]].add(edge[1]);
        g[edge[1]].add(edge[0]);
      }
    
      UnionFind uf = new UnionFind(n + 1);
      for (int u = 1; u <= n; u++) {
        for (Integer v : g[u]) {
          if (uf.connected(u, v)) {
            return false;
          }
          uf.union(v, g[u].get(0));
        }
      }
    
      return true;
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