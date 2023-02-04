//<p>用以太网线缆将&nbsp;<code>n</code>&nbsp;台计算机连接成一个网络，计算机的编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>。线缆用&nbsp;<code>connections</code>&nbsp;表示，其中&nbsp;<code>connections[i] = [a, b]</code>&nbsp;连接了计算机&nbsp;<code>a</code>&nbsp;和&nbsp;<code>b</code>。</p>
//
//<p>网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。</p>
//
//<p>给你这个计算机网络的初始布线&nbsp;<code>connections</code>，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回&nbsp;-1 。&nbsp;</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/11/sample_1_1677.png" style="height: 167px; width: 570px;" /></strong></p>
//
//<pre><strong>输入：</strong>n = 4, connections = [[0,1],[0,2],[1,2]]
//<strong>输出：</strong>1
//<strong>解释：</strong>拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/11/sample_2_1677.png" style="height: 175px; width: 660px;" /></strong></p>
//
//<pre><strong>输入：</strong>n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
//<strong>输出：</strong>-1
//<strong>解释：</strong>线缆数量不足。
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10^5</code></li> 
// <li><code>1 &lt;= connections.length &lt;= min(n*(n-1)/2, 10^5)</code></li> 
// <li><code>connections[i].length == 2</code></li> 
// <li><code>0 &lt;= connections[i][0], connections[i][1]&nbsp;&lt; n</code></li> 
// <li><code>connections[i][0] != connections[i][1]</code></li> 
// <li>没有重复的连接。</li> 
// <li>两台计算机不会通过多条线缆连接。</li> 
//</ul>
//
//<div><li>👍 203</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.LinkedList;
import java.util.List;

// 1319.连通网络的操作次数
// 开题时间：2023-01-08 16:17:11
public class NumberOfOperationsToMakeNetworkConnected {
  public static void main(String[] args) {
    Solution solution = new NumberOfOperationsToMakeNetworkConnected().new Solution();
    System.out.println(solution.makeConnected(11, Tools.to2DIntArray("[[1,4],[0,3],[1,3],[3,7],[2,7],[0,1],[2,4],[3,6],[5,6],[6,7],[4,7],[0,7],[5,7]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 并查集
    public int makeConnected9(int n, int[][] connections) {
      if (n - 1 > connections.length)
        return -1;
      
      UnionFind uf = new UnionFind(n);
      for (int[] connection : connections)
        uf.union(connection[0], connection[1]);
      
      return uf.cnt - 1;
    }
    
    //☆☆☆☆☆ dfs
    public int makeConnected(int n, int[][] connections) {
      if (n - 1 > connections.length)
        return -1;
      
      LinkedList<Integer>[] graph = new LinkedList[n];
      for (int i = 0; i < n; i++)
        graph[i] = new LinkedList<>();
      for (int[] connection : connections) {
        graph[connection[0]].add(connection[1]);
        graph[connection[1]].add(connection[0]);
      }
      
      int cnt = 0;
      boolean[] vis = new boolean[n];
      for (int i = 0; i < n; i++) {
        if (!vis[i]) {
          cnt++;
          dfs(graph, vis, i);
        }
      }
      
      return cnt - 1;
    }
    
    private void dfs(List<Integer>[] graph, boolean[] vis, int i) {
      vis[i] = true;
      for (int j : graph[i])
        if (!vis[j])
          dfs(graph, vis, j);
    }
  }
  
  class UnionFind {
    int[] root;
    int[] rank;
    int cnt;
    
    public UnionFind(int size) {
      cnt = size;
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
      if (rootX != rootY) {
        if (rank[rootX] == rank[rootY]) {
          root[rootX] = rootY;
          rank[rootY]++;
        } else if (rank[rootX] < rank[rootY])
          root[rootX] = rootY;
        else
          root[rootY] = rootX;
        cnt--;
      }
    }
    
    public boolean connected(int x, int y) {
      return find(x) == find(y);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}