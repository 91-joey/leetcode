//<p><code>n</code> 座城市，从 <code>0</code> 到 <code>n-1</code> 编号，其间共有 <code>n-1</code> 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。</p>
//
//<p>路线用 <code>connections</code> 表示，其中 <code>connections[i] = [a, b]</code> 表示从城市 <code>a</code> 到 <code>b</code> 的一条有向路线。</p>
//
//<p>今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。</p>
//
//<p>请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。</p>
//
//<p>题目数据 <strong>保证</strong> 每个城市在重新规划路线方向后都能到达城市 0 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/05/30/sample_1_1819.png" style="height: 150px; width: 240px;" /></strong></p>
//
//<pre><strong>输入：</strong>n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
//<strong>输出：</strong>3
//<strong>解释：</strong>更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/05/30/sample_2_1819.png" style="height: 60px; width: 380px;" /></strong></p>
//
//<pre><strong>输入：</strong>n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
//<strong>输出：</strong>2
//<strong>解释：</strong>更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>n = 3, connections = [[1,0],[2,0]]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= n &lt;= 5 * 10^4</code></li> 
// <li><code>connections.length == n-1</code></li> 
// <li><code>connections[i].length == 2</code></li> 
// <li><code>0 &lt;= connections[i][0], connections[i][1] &lt;= n-1</code></li> 
// <li><code>connections[i][0] != connections[i][1]</code></li> 
//</ul>
//
//<div><li>👍 93</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//1466.重新规划路线
//开题时间：2023-01-10 20:03:52
public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {
    public static void main(String[] args) {
        Solution solution = new ReorderRoutesToMakeAllPathsLeadToTheCityZero().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int ans = 0;

        //dfs + 看成无向图
        public int minReorder(int n, int[][] connections) {
            HashSet<Integer> set = new HashSet<>();
            for (int[] connection : connections)
                set.add(connection[0] * n + connection[1]);

            List<Integer>[] g = new ArrayList[n];
            for (int i = 0; i < n; i++)
                g[i] = new ArrayList<>();
            for (int[] connection : connections) {
                g[connection[0]].add(connection[1]);
                g[connection[1]].add(connection[0]);
            }

            boolean[] vis = new boolean[n];
            dfs(g, vis, set, n, 0);

            return ans;
        }

        private void dfs(List<Integer>[] g, boolean[] vis, HashSet<Integer> set, int n, int i) {
            vis[i] = true;
            for (int j : g[i]) {
                if (!vis[j]) {
                    dfs(g, vis, set, n, j);
                    if (!set.contains(j * n + i))
                        ans++;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}