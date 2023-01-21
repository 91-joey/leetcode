//<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的&nbsp;<strong>3 跑道道路</strong>&nbsp;，它总共包含&nbsp;<code>n + 1</code>&nbsp;个&nbsp;<strong>点</strong>&nbsp;，编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n</code>&nbsp;。一只青蛙从&nbsp;<code>0</code>&nbsp;号点第二条跑道&nbsp;<strong>出发</strong>&nbsp;，它想要跳到点&nbsp;<code>n</code>&nbsp;处。然而道路上可能有一些障碍。</p>
//
//<p>给你一个长度为 <code>n + 1</code>&nbsp;的数组&nbsp;<code>obstacles</code>&nbsp;，其中&nbsp;<code>obstacles[i]</code>&nbsp;（<b>取值范围从 0 到 3</b>）表示在点 <code>i</code>&nbsp;处的&nbsp;<code>obstacles[i]</code>&nbsp;跑道上有一个障碍。如果&nbsp;<code>obstacles[i] == 0</code>&nbsp;，那么点&nbsp;<code>i</code>&nbsp;处没有障碍。任何一个点的三条跑道中&nbsp;<strong>最多有一个</strong>&nbsp;障碍。</p>
//
//<ul> 
// <li>比方说，如果&nbsp;<code>obstacles[2] == 1</code>&nbsp;，那么说明在点 2 处跑道 1 有障碍。</li> 
//</ul>
//
//<p>这只青蛙从点 <code>i</code>&nbsp;跳到点 <code>i + 1</code>&nbsp;且跑道不变的前提是点 <code>i + 1</code>&nbsp;的同一跑道上没有障碍。为了躲避障碍，这只青蛙也可以在&nbsp;<strong>同一个</strong>&nbsp;点处&nbsp;<strong>侧跳</strong>&nbsp;到 <strong>另外一条</strong>&nbsp;跑道（这两条跑道可以不相邻），但前提是跳过去的跑道该点处没有障碍。</p>
//
//<ul> 
// <li>比方说，这只青蛙可以从点 3 处的跑道 3 跳到点 3 处的跑道 1 。</li> 
//</ul>
//
//<p>这只青蛙从点 0 处跑道 <code>2</code>&nbsp;出发，并想到达点 <code>n</code>&nbsp;处的 <strong>任一跑道</strong> ，请你返回 <strong>最少侧跳次数</strong>&nbsp;。</p>
//
//<p><strong>注意</strong>：点 <code>0</code>&nbsp;处和点 <code>n</code>&nbsp;处的任一跑道都不会有障碍。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/03/25/ic234-q3-ex1.png" style="width: 500px; height: 244px;" /> 
//<pre>
//<b>输入：</b>obstacles = [0,1,2,3,0]
//<b>输出：</b>2 
//<b>解释：</b>最优方案如上图箭头所示。总共有 2 次侧跳（红色箭头）。
//注意，这只青蛙只有当侧跳时才可以跳过障碍（如上图点 2 处所示）。
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/03/25/ic234-q3-ex2.png" style="width: 500px; height: 196px;" /> 
//<pre>
//<b>输入：</b>obstacles = [0,1,1,3,3,0]
//<b>输出：</b>0
//<b>解释：</b>跑道 2 没有任何障碍，所以不需要任何侧跳。
//</pre>
//
//<p><strong>示例 3：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/03/25/ic234-q3-ex3.png" style="width: 500px; height: 196px;" /> 
//<pre>
//<b>输入：</b>obstacles = [0,2,1,0,3,0]
//<b>输出：</b>2
//<b>解释：</b>最优方案如上图所示。总共有 2 次侧跳。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>obstacles.length == n + 1</code></li> 
// <li><code>1 &lt;= n &lt;= 5 * 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= obstacles[i] &lt;= 3</code></li> 
// <li><code>obstacles[0] == obstacles[n] == 0</code></li> 
//</ul>
//
//<div><li>👍 57</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//1824.最少侧跳次数
//开题时间：2023-01-21 10:22:13
public class MinimumSidewayJumps {
    public static void main(String[] args) {
        Solution solution = new MinimumSidewayJumps().new Solution();
        System.out.println(solution.minSideJumps(new int[]{0, 1, 2, 3, 0}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //BFS（路径总长 = 前跳路径长（固定为 n) + 侧跳路径长）
        public int minSideJumps9(int[] obstacles) {
            int n = obstacles.length;
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 1});
            boolean[][] vis = new boolean[n][3];
            vis[0][1] = true;
            for (int i = 0; i < n; i++)
                if (obstacles[i] != 0)
                    vis[i][obstacles[i] - 1] = true;

            int step = -n + 2;
            while (!q.isEmpty()) {
                for (int i = q.size(); i > 0; i--) {
                    int[] poll = q.poll();
                    int pos = poll[0], lane = poll[1];

                    if (pos == n - 2)
                        return step;

                    if (!vis[pos + 1][lane]) {
                        q.offer(new int[]{pos + 1, lane});
                        vis[pos + 1][lane] = true;
                    }

                    for (int j = 0; j < 3; j++) {
                        if (!vis[pos][j]) {
                            q.offer(new int[]{pos, j});
                            vis[pos][j] = true;
                        }
                    }
                }
                step++;
            }

            return -1;
        }

        //dp
        public int minSideJumps8(int[] obstacles) {
            int n = obstacles.length;
            int[][] f = new int[n][3];
            f[0][1] = 0;
            f[0][0] = f[0][2] = 1;
            for (int i = 1; i < n; i++)
                Arrays.fill(f[i], 0x3f3f3f3f);
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < 3; j++)
                    if (obstacles[i] != j + 1)
                        f[i][j] = f[i - 1][j];
                for (int j = 0; j < 3; j++)
                    if (obstacles[i] != j + 1)
                        f[i][j] = Math.min(f[i][j], Math.min(f[i][(j + 1) % 3], f[i][(j + 2) % 3]) + 1);
            }
            return Math.min(f[n - 1][0], Math.min(f[n - 1][1], f[n - 1][2]));
        }

        //dp优化（滚动数组）
        public int minSideJumps7(int[] obstacles) {
            int n = obstacles.length;
            int[] f = new int[3];
            f[0] = f[2] = 1;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < 3; j++)
                    if (obstacles[i] == j + 1)
                        f[j] = 0x3f3f3f3f;
                for (int j = 0; j < 3; j++)
                    if (obstacles[i] != j + 1)
                        f[j] = Math.min(f[j], Math.min(f[(j + 1) % 3], f[(j + 2) % 3]) + 1);
            }
            return Math.min(f[0], Math.min(f[1], f[2]));
        }

        /*
         * ☆☆☆☆☆ 贪心
         * 1.一直前跳，直到遇到障碍。
         * 2.此时，需要侧跳，有 2 个落地点（若有障碍，则包括障碍）
         *  我们从 2 个落地点同时出发，直到遇到障碍，我们选择没有遇到障碍的跑道，继续步骤1。
         */
        public int minSideJumps(int[] obstacles) {
            int n = obstacles.length;
            int ans = 0;
            for (int i = 0, lane = 1; i < n - 1; i++) {
                int lanePlus = lane + 1;
                if (obstacles[i + 1] != lanePlus)
                    continue;
                while (i < n - 1 && (obstacles[i] == lanePlus || obstacles[i] == 0))
                    i++;
                int one = lanePlus % 3;
                lane = obstacles[i] == one + 1 ? (lane + 2) % 3 : one;
                i--;
                ans++;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}