//<p>有 <code>n</code> 个城市通过一些航班连接。给你一个数组&nbsp;<code>flights</code> ，其中&nbsp;<code>flights[i] = [from<sub>i</sub>, to<sub>i</sub>, price<sub>i</sub>]</code> ，表示该航班都从城市 <code>from<sub>i</sub></code> 开始，以价格 <code>price<sub>i</sub></code> 抵达 <code>to<sub>i</sub></code>。</p>
//
//<p>现在给定所有的城市和航班，以及出发城市 <code>src</code> 和目的地 <code>dst</code>，你的任务是找到出一条最多经过 <code>k</code>&nbsp;站中转的路线，使得从 <code>src</code> 到 <code>dst</code> 的 <strong>价格最便宜</strong> ，并返回该价格。 如果不存在这样的路线，则输出 <code>-1</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入:</strong> 
// n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
// src = 0, dst = 2, k = 1
//<strong>输出:</strong> 200
//<strong>解释:</strong> 
// 城市航班图如下
//<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png" style="height: 180px; width: 246px;" />
//
// 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入:</strong> 
// n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
// src = 0, dst = 2, k = 0
//<strong>输出:</strong> 500
//<strong>解释:</strong> 
// 城市航班图如下
//<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png" style="height: 180px; width: 246px;" />
//
// 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 100</code></li> 
// <li><code>0 &lt;= flights.length &lt;= (n * (n - 1) / 2)</code></li> 
// <li><code>flights[i].length == 3</code></li> 
// <li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt; n</code></li> 
// <li><code>from<sub>i</sub> != to<sub>i</sub></code></li> 
// <li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>4</sup></code></li> 
// <li>航班没有重复，且不存在自环</li> 
// <li><code>0 &lt;= src, dst, k &lt; n</code></li> 
// <li><code>src != dst</code></li> 
//</ul>
//
//<div><li>👍 545</li><li>👎 0</li></div>
package _1_dataStructure.graph;

import _3_common.tool.Tools;

import java.util.Arrays;

// 787.K 站中转内最便宜的航班
// 开题时间：2022-12-29 17:50:26
public class CheapestFlightsWithinKStops {
  public static void main(String[] args) {
    Solution solution = new CheapestFlightsWithinKStops().new Solution();
    //        System.out.println(solution.findCheapestPrice(4, Tools.to2DIntArray("[[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]]"), 0, 3, 1));
    System.out.println(solution.findCheapestPrice(3, Tools.to2DIntArray("[[0,1,100],[1,2,100],[0,2,500]]"), 0, 2, 1));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int INF = 0x3f3f3f3f;
    
    // Bellman Ford + 邻接矩阵   O(k*n^2)    O(n^2)
    public int findCheapestPrice9(int n, int[][] flights, int src, int dst, int k) {
      int[][] graph = new int[n][n];
      for (int[] ints : graph)
        Arrays.fill(ints, INF);
      for (int i = 0; i < n; i++)
        graph[i][i] = 0;
      for (int[] flight : flights)
        graph[flight[0]][flight[1]] = flight[2];

            /*
            int bound = Math.min(k + 2, n);
            int[][] f = new int[bound][n];
            for (int[] ints : f)
                Arrays.fill(ints, INF);
            f[0][src] = 0;

            for (int i = 1; i < bound; i++)
                for (int j = 0; j < n; j++)
                    for (int p = 0; p < n; p++)
                        f[i][j] = Math.min(f[i][j], f[i - 1][p] + graph[p][j]);

            return f[bound - 1][dst] >= INF ? -1 : f[bound - 1][dst];
            */
      
      int[] f = new int[n];
      Arrays.fill(f, INF);
      f[src] = 0;
      
      int bound = Math.min(k + 2, n);
      for (int i = 1; i < bound; i++) {
        int[] copy = Arrays.copyOf(f, n);
        for (int j = 0; j < n; j++)
          for (int p = 0; p < n; p++)
            f[j] = Math.min(f[j], copy[p] + graph[p][j]);
      }
      
      return f[dst] >= INF ? -1 : f[dst];
    }
    
    
    //☆☆☆☆☆ Bellman Ford + 遍历边    O(k∗(n+m))  O(n)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
      int[] f = new int[n];
      Arrays.fill(f, INF);
      f[src] = 0;
      
      int bound = Math.min(k + 2, n);
      for (int i = 1; i < bound; i++) {
        int[] copy = Arrays.copyOf(f, n);
        for (int[] flight : flights)
          f[flight[1]] = Math.min(f[flight[1]], copy[flight[0]] + flight[2]);
      }
      
      return f[dst] >= INF ? -1 : f[dst];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}