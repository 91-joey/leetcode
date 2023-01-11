//<p>在一个有向图中，节点分别标记为&nbsp;<code>0, 1, ..., n-1</code>。图中每条边为红色或者蓝色，且存在自环或平行边。</p>
//
//<p><code>red_edges</code>&nbsp;中的每一个&nbsp;<code>[i, j]</code>&nbsp;对表示从节点 <code>i</code> 到节点 <code>j</code> 的红色有向边。类似地，<code>blue_edges</code>&nbsp;中的每一个&nbsp;<code>[i, j]</code>&nbsp;对表示从节点 <code>i</code> 到节点 <code>j</code> 的蓝色有向边。</p>
//
//<p>返回长度为 <code>n</code> 的数组&nbsp;<code>answer</code>，其中&nbsp;<code>answer[X]</code>&nbsp;是从节点&nbsp;<code>0</code>&nbsp;到节点&nbsp;<code>X</code>&nbsp;的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 <code>answer[x] = -1</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
//<strong>输出：</strong>[0,1,-1]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
//<strong>输出：</strong>[0,1,-1]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
//<strong>输出：</strong>[0,-1,-1]
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
//<strong>输出：</strong>[0,1,2]
//</pre>
//
//<p><strong>示例 5：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
//<strong>输出：</strong>[0,1,1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 100</code></li> 
// <li><code>red_edges.length &lt;= 400</code></li> 
// <li><code>blue_edges.length &lt;= 400</code></li> 
// <li><code>red_edges[i].length == blue_edges[i].length == 2</code></li> 
// <li><code>0 &lt;= red_edges[i][j], blue_edges[i][j] &lt; n</code></li> 
//</ul>
//
//<div><li>👍 123</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//1129.颜色交替的最短路径
//开题时间：2023-01-11 09:48:30
public class ShortestPathWithAlternatingColors {
    public static void main(String[] args) {
        Solution solution = new ShortestPathWithAlternatingColors().new Solution();
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(9, Tools.to2DIntArray("[[3,1],[2,3],[7,6],[5,1],[1,3],[8,1],[5,4],[8,4],[6,3],[4,7],[0,1],[7,8],[3,8]]"), Tools.to2DIntArray("[[4,1],[5,8],[3,7],[7,1],[1,8],[8,7],[5,4]]"))));
//        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(5, Tools.to2DIntArray("[[0,1],[1,2],[2,3],[3,4]]"), Tools.to2DIntArray("[[1,2],[2,3],[3,1]]"))));
//        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{1, 2}})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            //初始化结果数组
            int[] ans = new int[n];
            Arrays.fill(ans, Integer.MAX_VALUE);

            //红蓝边分别建立邻接表
            ArrayList<Integer>[] g1 = new ArrayList[n], g2 = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                g1[i] = new ArrayList<>();
                g2[i] = new ArrayList<>();
            }
            for (int[] edge : redEdges) g1[edge[0]].add(edge[1]);
            for (int[] edge : blueEdges) g2[edge[0]].add(edge[1]);

            //从节点 0 开始分别以红、蓝边出发进行 BFS
            bfs(g1, g2, ans, new boolean[n][2]);
            bfs(g2, g1, ans, new boolean[n][2]);

            for (int i = 0; i < n; i++)
                if (ans[i] == Integer.MAX_VALUE)
                    ans[i] = -1;
            return ans;
        }

        /**
         * 从节点 0 开始以g1中的边、g2中的边交替行进，进行BFS
         * @param g1 起始颜色所在的邻接表
         * @param g2 交替颜色所在的邻接表
         * @param ans 作为结果的最短路径数组
         * @param vis 访问标记数组
         *            vis[i][0]表示到达节点i时，下一条边的颜色为起始颜色，这一状态是否访问过
         *            vis[i][1]表示到达节点i时，下一条边的颜色为交替颜色，这一状态是否访问过
         */
        private void bfs(ArrayList<Integer>[] g1, ArrayList<Integer>[] g2, int[] ans, boolean[][] vis) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(0);
            ans[0] = 0;
            vis[0][0] = true;

            int step = 1;
            while (!q.isEmpty()) {
                takeOneStep(g1, ans, vis, q, step++, 1);
                takeOneStep(g2, ans, vis, q, step++, 0);
            }
        }

        private void takeOneStep(ArrayList<Integer>[] g, int[] ans, boolean[][] vis, Queue<Integer> q, int step, int color) {
            for (int i = q.size(); i > 0; i--)
                for (int j : g[q.poll()])
                    if (!vis[j][color]) {
                        ans[j] = Math.min(ans[j], step);
                        vis[j][color] = true;
                        q.offer(j);
                    }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}