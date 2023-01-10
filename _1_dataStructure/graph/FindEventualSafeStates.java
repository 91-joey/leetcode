//<p>有一个有 <code>n</code> 个节点的有向图，节点按 <code>0</code> 到 <code>n - 1</code> 编号。图由一个 <strong>索引从 0 开始</strong> 的 2D 整数数组&nbsp;<code>graph</code>表示，&nbsp;<code>graph[i]</code>是与节点 <code>i</code> 相邻的节点的整数数组，这意味着从节点 <code>i</code> 到&nbsp;<code>graph[i]</code>中的每个节点都有一条边。</p>
//
//<p>如果一个节点没有连出的有向边，则它是 <strong>终端节点</strong> 。如果没有出边，则节点为终端节点。如果从该节点开始的所有可能路径都通向 <strong>终端节点</strong> ，则该节点为 <strong>安全节点</strong> 。</p>
//
//<p>返回一个由图中所有 <strong>安全节点</strong> 组成的数组作为答案。答案数组中的元素应当按 <strong>升序</strong> 排列。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="Illustration of graph" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/03/17/picture1.png" /></p>
//
//<pre>
//<strong>输入：</strong>graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//<strong>输出：</strong>[2,4,5,6]
//<strong>解释：</strong>示意图如上。
//节点 5 和节点 6 是终端节点，因为它们都没有出边。
//从节点 2、4、5 和 6 开始的所有路径都指向节点 5 或 6 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//<strong>输出：</strong>[4]
//<strong>解释:</strong>
//只有节点 4 是终端节点，从节点 4 开始的所有路径都通向节点 4 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == graph.length</code></li> 
// <li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= graph[i].length &lt;= n</code></li> 
// <li><code>0 &lt;= graph[i][j] &lt;= n - 1</code></li> 
// <li><code>graph[i]</code> 按严格递增顺序排列。</li> 
// <li>图中可能包含自环。</li> 
// <li>图中边的数目在范围 <code>[1, 4 * 10<sup>4</sup>]</code> 内。</li> 
//</ul>
//
//<div><li>👍 367</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//802.找到最终的安全状态
//开题时间：2023-01-09 13:41:57
public class FindEventualSafeStates {
    public static void main(String[] args) {
        Solution solution = new FindEventualSafeStates().new Solution();
        int[][] arr = Tools.to2DIntArray("[[1,2],[2,3],[5],[0],[5],[],[]]");
        for (int[] ints : arr) {
            for (int i : ints) {
                System.out.println(i);
            }
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> eventualSafeNodesX(int[][] graph) {
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < graph.length; i++) {
                boolean isSafe = true;
                for (int out : graph[i])
                    isSafe = isSafe && dfs(graph, out, new boolean[graph.length]);
                if (isSafe)
                    ans.add(i);
            }

            return ans;
        }

        private boolean dfs(int[][] graph, int out, boolean[] vis) {
            boolean ans = !vis[out] || graph[out].length == 0;
            vis[out] = true;
            for (int i : graph[out])
                ans = ans && dfs(graph, i, vis);
            return ans;
        }

        /*
         * dfs + 三色标记法
         *      0 : 节点未被访问
         *      1 : 节点位于递归栈中，或者在某个环上
         *      2 : 节点搜索完毕，是一个安全节点
         */
        public List<Integer> eventualSafeNodes9(int[][] graph) {
            ArrayList<Integer> ans = new ArrayList<>();
            int[] color = new int[graph.length];
            for (int i = 0; i < graph.length; i++)
                if (isSafe(graph, color, i))
                    ans.add(i);

            return ans;
        }

        //☆☆☆☆☆ 反向图 + 拓扑排序
        public List<Integer> eventualSafeNodes(int[][] graph) {
            int n = graph.length;
            //存反向图，记录节点入度
            List<Integer>[] g = new ArrayList[n];
            int[] inDeg = new int[n];
            for (int i = 0; i < n; i++)
                g[i] = new ArrayList<>();
            for (int i = 0; i < n; i++)
                for (int j : graph[i]) {
                    g[j].add(i);
                    inDeg[i]++;
                }

            //拓扑排序，更新节点入度
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < n; i++)
                if (inDeg[i] == 0)
                    q.offer(i);
            while (!q.isEmpty())
                for (int i : g[q.poll()])
                    if (--inDeg[i] == 0)
                        q.offer(i);

            //最后入度为 0 的节点即为安全节点
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if (inDeg[i] == 0)
                    ans.add(i);
            return ans;
        }

        private boolean isSafe(int[][] graph, int[] color, int i) {
            if (color[i] > 0)
                return color[i] == 2;

            color[i] = 1;
            for (int j : graph[i])
                if (!isSafe(graph, color, j))
                    return false;

            color[i] = 2;
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}