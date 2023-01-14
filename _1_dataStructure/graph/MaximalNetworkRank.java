//<p><code>n</code> 座城市和一些连接这些城市的道路 <code>roads</code> 共同组成一个基础设施网络。每个 <code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 都表示在城市 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间有一条双向道路。</p>
//
//<p>两座不同城市构成的 <strong>城市对</strong> 的 <strong>网络秩</strong> 定义为：与这两座城市 <strong>直接</strong> 相连的道路总数。如果存在一条道路直接连接这两座城市，则这条道路只计算 <strong>一次</strong> 。</p>
//
//<p>整个基础设施网络的 <strong>最大网络秩</strong> 是所有不同城市对中的 <strong>最大网络秩</strong> 。</p>
//
//<p>给你整数 <code>n</code> 和数组 <code>roads</code>，返回整个基础设施网络的 <strong>最大网络秩</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/10/11/ex1.png" style="width: 292px; height: 172px;" /></strong></p>
//
//<pre>
//<strong>输入：</strong>n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
//<strong>输出：</strong>4
//<strong>解释：</strong>城市 0 和 1 的网络秩是 4，因为共有 4 条道路与城市 0 或 1 相连。位于 0 和 1 之间的道路只计算一次。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/10/11/ex2.png" style="width: 292px; height: 172px;" /></strong></p>
//
//<pre>
//<strong>输入：</strong>n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
//<strong>输出：</strong>5
//<strong>解释：</strong>共有 5 条道路与城市 1 或 2 相连。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
//<strong>输出：</strong>5
//<strong>解释：</strong>2 和 5 的网络秩为 5，注意并非所有的城市都需要连接起来。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= n &lt;= 100</code></li> 
// <li><code>0 &lt;= roads.length &lt;= n * (n - 1) / 2</code></li> 
// <li><code>roads[i].length == 2</code></li> 
// <li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt;= n-1</code></li> 
// <li><code>a<sub>i</sub>&nbsp;!=&nbsp;b<sub>i</sub></code></li> 
// <li>每对城市之间 <strong>最多只有一条</strong>&nbsp;道路相连</li> 
//</ul>
//
//<div><li>👍 38</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//1615.最大网络秩
//开题时间：2023-01-14 11:02:53
public class MaximalNetworkRank {
    public static void main(String[] args) {
        Solution solution = new MaximalNetworkRank().new Solution();
//        System.out.println(solution.maximalNetworkRank(5, Tools.to2DIntArray("[[2,3],[0,3],[0,4],[4,1]]")));
        System.out.println(solution.maximalNetworkRank(8, Tools.to2DIntArray("[[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]")));
        System.out.println(-1 / 2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力枚举  m+n^2   n^2
        public int maximalNetworkRank9(int n, int[][] roads) {
            int[] inDeg = new int[n];
            boolean[][] g = new boolean[n][n];
            for (int[] road : roads) {
                inDeg[road[0]]++;
                inDeg[road[1]]++;
                g[road[0]][road[1]] = g[road[1]][road[0]] = true;
            }

            int max = 0;
            for (int i = 0; i < n - 1; i++)
                for (int j = i + 1; j < n; j++)
                    max = Math.max(max, inDeg[i] + inDeg[j] - (g[i][j] ? 1 : 0));

            return max;
        }

        //枚举优化（统计最大次大入度值的顶点集合）  m + n
        public int maximalNetworkRank(int n, int[][] roads) {
            int[] inDeg = new int[n];
            Set<Integer>[] g = new HashSet[n];
            for (int i = 0; i < n; i++)
                g[i] = new HashSet<>();
            for (int[] road : roads) {
                inDeg[road[0]]++;
                inDeg[road[1]]++;
                g[road[0]].add(road[1]);
                g[road[1]].add(road[0]);
            }

            int first = 0, second = -1;
            for (int i = 0; i < n; i++)
                if (first < inDeg[i]) {
                    second = first;
                    first = inDeg[i];
                } else if (second < inDeg[i])
                    second = inDeg[i];

            List<Integer> firstList = new ArrayList<>(), secondList = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if (first == inDeg[i])
                    firstList.add(i);
                else if (second == inDeg[i])
                    secondList.add(i);

            int size = firstList.size();
            if (size == 1) {
                int firstIdx = firstList.get(0);
                for (int secondIdx : secondList)
                    if (!g[firstIdx].contains(secondIdx))
                        return first + second;
                return first + second - 1;
            } else {
                if (size * (size - 1) / 2 > roads.length)
                    return first * 2;
                for (int i = 0; i < size - 1; i++)
                    for (int j = i + 1; j < size; j++)
                        if (!g[firstList.get(i)].contains(firstList.get(j)))
                            return first * 2;
                return first * 2 - 1;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}