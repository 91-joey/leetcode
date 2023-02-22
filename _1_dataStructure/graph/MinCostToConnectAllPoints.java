//<p>给你一个<code>points</code>&nbsp;数组，表示 2D 平面上的一些点，其中&nbsp;<code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;。</p>
//
//<p>连接点&nbsp;<code>[x<sub>i</sub>, y<sub>i</sub>]</code> 和点&nbsp;<code>[x<sub>j</sub>, y<sub>j</sub>]</code>&nbsp;的费用为它们之间的 <strong>曼哈顿距离</strong>&nbsp;：<code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>&nbsp;，其中&nbsp;<code>|val|</code>&nbsp;表示&nbsp;<code>val</code>&nbsp;的绝对值。</p>
//
//<p>请你返回将所有点连接的最小总费用。只有任意两点之间 <strong>有且仅有</strong>&nbsp;一条简单路径时，才认为所有点都已连接。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2020/08/26/d.png" style="height:268px; width:214px; background:#e5e5e5" /></p>
//
//<pre>
//<strong>输入：</strong>points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//<strong>输出：</strong>20
//<strong>解释：
//</strong><img alt="" src="https://assets.leetcode.com/uploads/2020/08/26/c.png" style="height:268px; width:214px; background:#e5e5e5" />
// 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
// 注意到任意两个点之间只有唯一一条路径互相到达。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>points = [[3,12],[-2,5],[-4,1]]
//<strong>输出：</strong>18
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>points = [[0,0],[1,1],[1,0],[-1,1]]
//<strong>输出：</strong>4
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>points = [[-1000000,-1000000],[1000000,1000000]]
//<strong>输出：</strong>4000000
//</pre>
//
//<p><strong>示例 5：</strong></p>
//
//<pre>
//<strong>输入：</strong>points = [[0,0]]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= points.length &lt;= 1000</code></li> 
// <li><code>-10<sup>6</sup>&nbsp;&lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>6</sup></code></li> 
// <li>所有点&nbsp;<code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;两两不同。</li> 
//</ul>
//
//<div><li>👍 256</li><li>👎 0</li></div>
package _1_dataStructure.graph;

import _3_common.tool.Tools;

import java.util.Comparator;
import java.util.PriorityQueue;

// 1584.连接所有点的最小费用
// 开题时间：2022-12-24 17:22:48
public class MinCostToConnectAllPoints {
  public static void main(String[] args) {
    Solution solution = new MinCostToConnectAllPoints().new Solution();
    System.out.println(solution.minCostConnectPoints(Tools.to2DIntArray("[[0,0],[2,2],[3,10],[5,2],[7,0]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // Kruskal最小生成树 + 并查集
    public int minCostConnectPoints9(int[][] points) {
      int n = points.length;
      if (n <= 1)
        return 0;
      PriorityQueue<int[]> pq = new PriorityQueue<>(n * (n - 1) / 2, Comparator.comparingInt(arr -> arr[2]));
      for (int i = 0; i < n - 1; i++)
        for (int j = i + 1; j < n; j++)
          pq.offer(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});
      
      UnionFind uf = new UnionFind(n);
      int ans = 0;
      while (n > 1) {
        int[] poll = pq.poll();
        if (!uf.connected(poll[0], poll[1])) {
          uf.union(poll[0], poll[1]);
          ans += poll[2];
          n--;
        }
      }
      return ans;
    }
    
    //☆☆☆☆☆ n^2logn （优化）Kruskal最小生成树 + 并查集 + 优先队列
    public int minCostConnectPoints8(int[][] points) {
      int n = points.length;
      if (n <= 1)
        return 0;
      PriorityQueue<Edge> pq = new PriorityQueue<>(n * (n - 1) / 2, Comparator.comparingInt(edge -> edge.weight));
      for (int i = 0; i < n - 1; i++)
        for (int j = i + 1; j < n; j++)
          // 定义类，相较于数组更清晰不易错
          pq.offer(new Edge(i, j, manhattan(points, i, j)));
      
      UnionFind2 uf = new UnionFind2(n);
      int ans = 0;
      while (n > 1) {
        Edge poll = pq.poll();
        // union 函数兼具 connected 函数功能，能检查两点是否连通、若未连通则进行连通操作
        if (uf.union(poll.start, poll.end)) {
          ans += poll.weight;
          n--;
        }
      }
      return ans;
    }
    
    // n^2logn prim + 优先队列
    public int minCostConnectPoints(int[][] points) {
      int n = points.length;
      if (n <= 1)
        return 0;
      PriorityQueue<Edge> pq = new PriorityQueue<>(n * (n - 1) / 2, Comparator.comparingInt(edge -> edge.weight));
      for (int i = 1; i < n; i++)
        pq.offer(new Edge(0, i, manhattan(points, 0, i)));
      
      boolean[] vis = new boolean[n];
      vis[0] = true;
      int ans = 0;
      for (int i = 0; i < n - 1; ) {
        Edge poll = pq.poll();
        if (!vis[poll.end]) {
          vis[poll.end] = true;
          ans += poll.weight;
          for (int j = 1; j < n; j++)
            if (!vis[j])
              pq.offer(new Edge(poll.end, j, manhattan(points, poll.end, j)));
          i++;
        }
      }
      
      return ans;
    }
    
    public static int manhattan(int x0, int y0, int x1, int y1) {
      return Math.abs(x0 - x1) + Math.abs(y0 - y1);
    }
    
    public static int manhattan(int[] p0, int[] p1) {
      return manhattan(p0[0], p0[1], p1[0], p1[1]);
    }
    
    public static int manhattan(int[][] points, int i, int j) {
      return manhattan(points[i], points[j]);
    }
  }
  
  class Edge {
    int start, end, weight;
    
    public Edge(int start, int end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
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
  
  class UnionFind2 {
    int[] root;
    int[] rank;
    
    public UnionFind2(int size) {
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
    
    public boolean union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX == rootY)
        return false;
      
      if (rank[rootX] == rank[rootY]) {
        root[rootX] = rootY;
        rank[rootY]++;
      } else if (rank[rootX] < rank[rootY])
        root[rootX] = rootY;
      else
        root[rootY] = rootX;
      
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}