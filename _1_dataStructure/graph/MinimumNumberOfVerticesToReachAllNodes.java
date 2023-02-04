//<p>给你一个 <strong>有向无环图</strong>&nbsp;， <code>n</code>&nbsp;个节点编号为 <code>0</code>&nbsp;到 <code>n-1</code>&nbsp;，以及一个边数组 <code>edges</code>&nbsp;，其中 <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>]</code>&nbsp;表示一条从点&nbsp;&nbsp;<code>from<sub>i</sub></code>&nbsp;到点&nbsp;<code>to<sub>i</sub></code>&nbsp;的有向边。</p>
//
//<p>找到最小的点集使得从这些点出发能到达图中所有点。题目保证解存在且唯一。</p>
//
//<p>你可以以任意顺序返回这些节点编号。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/08/22/5480e1.png" style="height: 181px; width: 231px;" /></p>
//
//<pre><strong>输入：</strong>n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
//<strong>输出：</strong>[0,3]
//<strong>解释：</strong>从单个节点出发无法到达所有节点。从 0 出发我们可以到达 [0,1,2,5] 。从 3 出发我们可以到达 [3,4,2,5] 。所以我们输出 [0,3] 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/08/22/5480e2.png" style="height: 201px; width: 201px;" /></p>
//
//<pre><strong>输入：</strong>n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
//<strong>输出：</strong>[0,2,3]
//<strong>解释：</strong>注意到节点 0，3 和 2 无法从其他节点到达，所以我们必须将它们包含在结果点集中，这些点都能到达节点 1 和 4 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= n &lt;= 10^5</code></li> 
// <li><code>1 &lt;= edges.length &lt;= min(10^5, n * (n - 1) / 2)</code></li> 
// <li><code>edges[i].length == 2</code></li> 
// <li><code>0 &lt;= from<sub>i,</sub>&nbsp;to<sub>i</sub> &lt; n</code></li> 
// <li>所有点对&nbsp;<code>(from<sub>i</sub>, to<sub>i</sub>)</code>&nbsp;互不相同。</li> 
//</ul>
//
//<div><li>👍 60</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import java.util.ArrayList;
import java.util.List;

// 1557.可以到达所有点的最少点数目
// 开题时间：2023-01-13 10:43:51
public class MinimumNumberOfVerticesToReachAllNodes {
  public static void main(String[] args) {
    Solution solution = new MinimumNumberOfVerticesToReachAllNodes().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 答案为所有入度为 0 的节点集
    public List<Integer> findSmallestSetOfVertices9(int n, List<List<Integer>> edges) {
      int[] inDeg = new int[n];
      for (List<Integer> edge : edges)
        inDeg[edge.get(1)]++;
      
      ArrayList<Integer> ans = new ArrayList<>();
      for (int i = 0; i < n; i++)
        if (inDeg[i] == 0)
          ans.add(i);
      
      return ans;
    }
    
    //☆☆☆☆☆ 答案为所有入度为 0 的节点集（布尔数组优化）
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
      boolean[] hasInDeg = new boolean[n];
      for (List<Integer> edge : edges)
        hasInDeg[edge.get(1)] = true;
      
      ArrayList<Integer> ans = new ArrayList<>();
      for (int i = 0; i < n; i++)
        if (!hasInDeg[i])
          ans.add(i);
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}