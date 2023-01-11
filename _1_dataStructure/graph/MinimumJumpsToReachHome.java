//<p>有一只跳蚤的家在数轴上的位置&nbsp;<code>x</code>&nbsp;处。请你帮助它从位置&nbsp;<code>0</code>&nbsp;出发，到达它的家。</p>
//
//<p>跳蚤跳跃的规则如下：</p>
//
//<ul> 
// <li>它可以 <strong>往前</strong> 跳恰好 <code>a</code>&nbsp;个位置（即往右跳）。</li> 
// <li>它可以 <strong>往后</strong>&nbsp;跳恰好 <code>b</code>&nbsp;个位置（即往左跳）。</li> 
// <li>它不能 <strong>连续</strong> 往后跳 <code>2</code> 次。</li> 
// <li>它不能跳到任何&nbsp;<code>forbidden</code>&nbsp;数组中的位置。</li> 
//</ul>
//
//<p>跳蚤可以往前跳 <strong>超过</strong>&nbsp;它的家的位置，但是它 <strong>不能跳到负整数</strong>&nbsp;的位置。</p>
//
//<p>给你一个整数数组&nbsp;<code>forbidden</code>&nbsp;，其中&nbsp;<code>forbidden[i]</code>&nbsp;是跳蚤不能跳到的位置，同时给你整数&nbsp;<code>a</code>，&nbsp;<code>b</code>&nbsp;和&nbsp;<code>x</code>&nbsp;，请你返回跳蚤到家的最少跳跃次数。如果没有恰好到达 <code>x</code>&nbsp;的可行方案，请你返回 <code>-1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
//<b>输出：</b>3
//<b>解释：</b>往前跳 3 次（0 -&gt; 3 -&gt; 6 -&gt; 9），跳蚤就到家了。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
//<b>输出：</b>-1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<b>输入：</b>forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
//<b>输出：</b>2
//<b>解释：</b>往前跳一次（0 -&gt; 16），然后往回跳一次（16 -&gt; 7），跳蚤就到家了。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= forbidden.length &lt;= 1000</code></li> 
// <li><code>1 &lt;= a, b, forbidden[i] &lt;= 2000</code></li> 
// <li><code>0 &lt;= x &lt;= 2000</code></li> 
// <li><code>forbidden</code>&nbsp;中所有位置互不相同。</li> 
// <li>位置&nbsp;<code>x</code>&nbsp;不在 <code>forbidden</code>&nbsp;中。</li> 
//</ul>
//
//<div><li>👍 72</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//1654.到家的最少跳跃次数
//开题时间：2023-01-11 15:34:12
public class MinimumJumpsToReachHome {
    public static void main(String[] args) {
        Solution solution = new MinimumJumpsToReachHome().new Solution();
        System.out.println(solution.minimumJumps(new int[]{1998}, 1999, 2000, 2000));
//        System.out.println(solution.minimumJumps(new int[]{198,159,151,166,33,155,90,43,104,102,186,137,2,3,24,139,150,5}, 144, 89, 199));
//        System.out.println(solution.minimumJumps(new int[]{1, 6, 2, 14, 5, 17, 4}, 16, 9, 7));
//        System.out.println(solution.minimumJumps(new int[]{8, 3, 16, 6, 12, 20}, 15, 13, 11));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumJumpsX(int[] forbidden, int a, int b, int x) {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 0});
//            boolean[][] vis = new boolean[4001][2];
            HashSet<Integer> vis = new HashSet<>();
            for (int i : forbidden) {
//                vis[i][0] = vis[i][1] = true;
                vis.add(i << 1);
                vis.add((i << 1) | 1);
            }
//            vis[0][0] = true;
            vis.add(0);

            HashSet<Integer> set = new HashSet<>();
            set.add(0);

            int max = Math.max(Arrays.stream(forbidden).max().getAsInt() + a + b, x + b);

            int step = 0;
            while (!q.isEmpty()) {
//                if (step > 1 && a >= b && q.peek()[0] > x)
                if (q.peek()[0] > max)
                    return -1;
                for (int i = q.size(); i > 0; i--) {
                    int[] poll = q.poll();
                    if (poll[0] == x)
                        return step;

                    if (poll[1] == 0 && poll[0] - b >= 0 && !vis.contains(((poll[0] - b) << 1) | 1)) {
//                    if (poll[1] == 0 && poll[0] - b >= 0 && !vis[poll[0] - b][1]) {
                        vis.add(((poll[0] - b) << 1) | 1);
                        set.add(poll[0] - b);
                        q.offer(new int[]{poll[0] - b, 1});
                    }
                    if (!vis.contains((poll[0] + a) << 1)) {
//                    if (!vis[poll[0] + a][0]) {
//                        vis[poll[0] + a][0] = true;
                        vis.add((poll[0] + a) << 1);
                        set.add(poll[0] + a);
                        q.offer(new int[]{poll[0] + a, 0});
                    }
                }
                step++;
            }

            return -1;
        }

        /*
         * ☆☆☆☆☆ bfs + 限定右边界（先向前跳几次，再向前向后交替跳跃，这种方案和先向前向后交替跳跃、再向前跳跃几次是等价的）
         * 以下两种方案是等价的：
         * - 先向前跳跃几次，再向前向后交替跳跃
         * - 先向前向后交替跳跃、再向前跳跃几次
         * 当 `a<b` 时，向前向后交替跳跃一次会回退，方案一的极值会大于方案二。
         */
        public int minimumJumps(int[] forbidden, int a, int b, int x) {
            int bound = Math.max(Arrays.stream(forbidden).max().getAsInt() + a + b, x + b) + 1;
            boolean[][] vis = new boolean[bound][2];
            for (int i : forbidden)
                vis[i][0] = vis[i][1] = true;
            vis[0][0] = true;

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 0});
            int step = 0;
            while (!q.isEmpty()) {
                for (int i = q.size(); i > 0; i--) {
                    int[] poll = q.poll();
                    int cur = poll[0];
                    if (cur == x)
                        return step;

                    if (poll[1] == 0 && cur - b >= 0 && !vis[cur - b][1]) {
                        vis[cur - b][1] = true;
                        q.offer(new int[]{cur - b, 1});
                    }
                    if (cur + a < bound && !vis[cur + a][0]) {
                        vis[cur + a][0] = true;
                        q.offer(new int[]{cur + a, 0});
                    }
                }
                step++;
            }

            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}