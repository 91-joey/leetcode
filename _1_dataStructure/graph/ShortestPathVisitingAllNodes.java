//<p>存在一个由 <code>n</code> 个节点组成的无向连通图，图中的节点按从 <code>0</code> 到 <code>n - 1</code> 编号。</p>
//
//<p>给你一个数组 <code>graph</code> 表示这个图。其中，<code>graph[i]</code> 是一个列表，由所有与节点 <code>i</code> 直接相连的节点组成。</p>
//
//<p>返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。</p>
//
//<p>&nbsp;</p>
//
//<ol> 
//</ol>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/05/12/shortest1-graph.jpg" style="width: 222px; height: 183px;" /> 
//<pre>
//<strong>输入：</strong>graph = [[1,2,3],[0],[0],[0]]
//<strong>输出：</strong>4
//<strong>解释：</strong>一种可能的路径为 [1,0,2,0,3]</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/05/12/shortest2-graph.jpg" style="width: 382px; height: 222px;" /></p>
//
//<pre>
//<strong>输入：</strong>graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//<strong>输出：</strong>4
//<strong>解释：</strong>一种可能的路径为 [0,1,4,2,3]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == graph.length</code></li> 
// <li><code>1 &lt;= n &lt;= 12</code></li> 
// <li><code>0 &lt;= graph[i].length &lt;&nbsp;n</code></li> 
// <li><code>graph[i]</code> 不包含 <code>i</code></li> 
// <li>如果 <code>graph[a]</code> 包含 <code>b</code> ，那么 <code>graph[b]</code> 也包含 <code>a</code></li> 
// <li>输入的图总是连通图</li> 
//</ul>
//
//<div><li>👍 346</li><li>👎 0</li></div>
package _1_dataStructure.graph;

import _3_common.tool.Tools;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 847.访问所有节点的最短路径
// 开题时间：2023-01-12 15:50:21
public class ShortestPathVisitingAllNodes {
  public static void main(String[] args) {
    Solution solution = new ShortestPathVisitingAllNodes().new Solution();
    System.out.println(solution.shortestPathLength(Tools.to2DIntArray("[[7],[3],[3,9],[1,2,4,5,7,11],[3],[3],[9],[3,10,8,0],[7],[11,6,2],[7],[3,9]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int shortestPathLengthX(int[][] graph) {
      int n = graph.length;
      Queue<State> q = new LinkedList<>();
      for (int i = 0; i < n; i++) {
        int[] vis = new int[n];
        vis[i]++;
        q.offer(new State(i, vis));
      }
      
      int step = 0;
      int bound = 2 * n - 3;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          State state = q.poll();
          if (isAllVisited(state.vis))
            return step;
          
          for (int out : graph[state.node]) {
            if (state.vis[out] < bound) {
              int[] copy = Arrays.copyOf(state.vis, n);
              copy[out]++;
              q.offer(new State(out, copy));
            }
          }
        }
        step++;
      }
      
      return step;
    }
    
    public int shortestPathLengthXX(int[][] graph) {
      int n = graph.length;
      Queue<State2> q = new LinkedList<>();
      for (int i = 0; i < n; i++) {
        boolean[] visNode = new boolean[n];
        visNode[i] = true;
        q.offer(new State2(i, new boolean[n][n], visNode));
      }
      
      int step = 0;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          State2 state = q.poll();
          if (isAllVisited(state.visNode))
            return step;
          
          for (int out : graph[state.node]) {
            if (!state.visEdge[state.node][out]) {
              //                            boolean[][] copyVisEdge = Arrays.copyOf(state.visEdge, n);
              //                            boolean[][] copyVisEdge = state.visEdge.clone();
              boolean[][] copyVisEdge = new boolean[n][];
              for (int j = 0; j < n; j++)
                copyVisEdge[j] = Arrays.copyOf(state.visEdge[j], n);
              boolean[] copyVisNode = Arrays.copyOf(state.visNode, n);
              copyVisEdge[state.node][out] = true;
              copyVisNode[out] = true;
              q.offer(new State2(out, copyVisEdge, copyVisNode));
            }
          }
        }
        step++;
      }
      
      return step;
    }
    
    /*
     * ☆☆☆☆☆ 多源bfs + 状压   2^n * n^2   2^n * n
     * 状态定义：0b(state) 某位为 1，表示某节点已访问
     * 关键：以同一状态到达同一个节点，是多余、重复的搜索路径。
     */
    public int shortestPathLength9(int[][] graph) {
      int n = graph.length;
      Queue<int[]> q = new LinkedList<>();
      boolean[][] vis = new boolean[1 << n][n];
      for (int i = 0; i < n; i++) {
        q.offer(new int[]{1 << i, i});
        vis[1 << i][i] = true;
      }
      
      int step = 0;
      int t = (1 << n) - 1;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          int[] poll = q.poll();
          int state = poll[0];
          int node = poll[1];
          if (state == t)
            return step;
          
          for (int out : graph[node]) {
            int newState = state | (1 << out);
            if (!vis[newState][out]) {
              q.offer(new int[]{newState, out});
              vis[newState][out] = true;
            }
          }
        }
        step++;
      }
      
      return step;
    }
    
    public int shortestPathLengthXXX(int[][] graph) {
      int n = graph.length;
      boolean[][] g = new boolean[n][n];
      for (int u = 0; u < n; u++) {
        for (int v : graph[u]) {
          g[u][v] = g[v][u] = true;
        }
      }
      
      int[][] f = new int[1 << n][n];
      for (int s = 0; s < 1 << n; s++) {
        Arrays.fill(f[s], 0x3f3f3f3f);
      }
      for (int i = 0; i < n; i++) {
        f[1 << i][i] = 0;
      }
      
      for (int s = 1; s < 1 << n; s++) {
        for (int i = 0; i < n; i++) {
          if ((s & (1 << i)) == 0) {
            continue;
          }
          for (int j = 0; j < n; j++) {
            if ((s & (1 << j)) == 0 || !g[i][j]) {
              continue;
            }
            f[s][i] = Math.min(f[s][i], f[s ^ (1 << i)][j] + 1);
          }
        }
      }
      
      return Arrays.stream(f[(1 << n) - 1]).min().getAsInt();
    }
    
    /*
     * 预处理点对最短路(n^3) + 状压动规(n^2*2^n)
     *
     * 状态定义：f[s][u]表示访问了状态 s 表示的点、以点 u 结尾的最短路径（注意：可能会访问状态 s 表示以外的点）
     * 状态转移：f[s][u] = Math.min(f[s][u], f[s ^ (1 << u)][v] + d[u][v]) if u ∈ s && v ∈ s && u != v
     *         d[u][v]为点 u、v 间的最短距离，用 Floyd 算法预处理求得
     * 初始化　：f[1 << i][i] = 0 for 0 <= i < n
     *         f[s][u] = 0x3f3f3f3f for others
     * 答案　　：min(f[(1 << n) - 1]))
     */
    public int shortestPathLength(int[][] graph) {
      int n = graph.length;
      // 使用 floyd 算法预处理出所有点对之间的最短路径长度
      int[][] d = new int[n][n];
      for (int i = 0; i < n; ++i) {
        Arrays.fill(d[i], n);
      }
      for (int i = 0; i < n; ++i) {
        for (int j : graph[i]) {
          d[i][j] = 1;
        }
      }
      for (int k = 0; k < n; ++k) {
        for (int i = 0; i < n; ++i) {
          for (int j = 0; j < n; ++j) {
            d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
          }
        }
      }
      
      int[][] f = new int[1 << n][n];
      for (int s = 0; s < 1 << n; s++) {
        Arrays.fill(f[s], 0x3f3f3f3f);
      }
      for (int i = 0; i < n; i++) {
        f[1 << i][i] = 0;
      }
      
      for (int s = 1; s < 1 << n; s++) {
        for (int u = 0; u < n; u++) {
          if ((s & (1 << u)) == 0) {
            continue;
          }
          for (int v = 0; v < n; v++) {
            if ((s & (1 << v)) == 0 || u == v) {
              continue;
            }
            f[s][u] = Math.min(f[s][u], f[s ^ (1 << u)][v] + d[u][v]);
          }
        }
      }
      
      return Arrays.stream(f[(1 << n) - 1]).min().getAsInt();
    }
    
    private boolean isAllVisited(int[] vis) {
      for (int cnt : vis)
        if (cnt <= 0)
          return false;
      return true;
    }
    
    private boolean isAllVisited(boolean[] vis) {
      for (boolean b : vis)
        if (!b)
          return false;
      return true;
    }
  }
  
  class State {
    int node;
    int[] vis;
    
    public State(int node, int[] vis) {
      this.node = node;
      this.vis = vis;
    }
  }
  
  class State2 {
    int node;
    boolean[][] visEdge;
    boolean[] visNode;
    
    public State2(int node, boolean[][] visEdge, boolean[] visNode) {
      this.node = node;
      this.visEdge = visEdge;
      this.visNode = visNode;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}