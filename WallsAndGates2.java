//<p>你被给定一个&nbsp;<code>m × n</code>&nbsp;的二维网格 <code>rooms</code> ，网格中有以下三种可能的初始化值：</p>
//
//<ol> 
// <li><code>-1</code>&nbsp;表示墙或是障碍物</li> 
// <li><code>0</code>&nbsp;表示一扇门</li> 
// <li><code>INF</code>&nbsp;无限表示一个空的房间。然后，我们用&nbsp;<code>2<sup>31</sup> - 1 = 2147483647</code>&nbsp;代表&nbsp;<code>INF</code>。你可以认为通往门的距离总是小于&nbsp;<code>2147483647</code>&nbsp;的。</li> 
//</ol>
//
//<p>你要给每个空房间位上填上该房间到&nbsp;<strong>最近门的距离</strong> ，如果无法到达门，则填&nbsp;<code>INF</code>&nbsp;即可。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/01/03/grid.jpg" style="width: 500px; height: 223px;" /> 
//<pre>
//<strong>输入：</strong>rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
//<strong>输出：</strong>[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>rooms = [[-1]]
//<strong>输出：</strong>[[-1]]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>rooms = [[2147483647]]
//<strong>输出：</strong>[[2147483647]]
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>rooms = [[0]]
//<strong>输出：</strong>[[0]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == rooms.length</code></li> 
// <li><code>n == rooms[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 250</code></li> 
// <li><code>rooms[i][j]</code> 是 <code>-1</code>、<code>0</code> 或 <code>2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 212</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//286.墙与门
//开题时间：2022-08-14 17:21:17
//2.官解一（暴力） 求解每个房间到门的最短距离（有重复计算）    m^2*n^2 m*n
public class WallsAndGates2 {

    public static void main(String[] args) {
        Solution solution = new WallsAndGates2().new Solution();
        solution.wallsAndGates(new int[][]{{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private static final int ROOM = Integer.MAX_VALUE;
        private static final int GATE = 0;
        private static final int WALL = -1;
        private final int[][] DIRECTIONS = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };

        public void wallsAndGates(int[][] rooms) {
            int m = rooms.length;
            int n = rooms[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (rooms[i][j] == ROOM) {
                        rooms[i][j] = distanceToNearestGate(rooms, m, n, i, j);
                    }
                }
            }
        }

        private int distanceToNearestGate(int[][] rooms, int m, int n, int i, int j) {
            Queue<int[]> queue = new LinkedList<>();
            int[][] distancesFromOne = new int[m][n];

            queue.offer(new int[]{i, j});

            while (!queue.isEmpty()) {
                int[] head = queue.poll();
                int x = head[0];
                int y = head[1];
                for (int[] direction : DIRECTIONS) {
                    int xChild = x + direction[0];
                    int yChild = y + direction[1];
                    if (0 <= xChild && xChild < m && 0 <= yChild && yChild < n &&
                            rooms[xChild][yChild] != WALL &&
                            !(xChild == i && yChild == j) && distancesFromOne[xChild][yChild] == 0) {
                        distancesFromOne[xChild][yChild] = distancesFromOne[x][y] + 1;
                        if (rooms[xChild][yChild] == GATE) {
                            return distancesFromOne[xChild][yChild];
                        }
                        queue.offer(new int[]{xChild, yChild});
                    }
                }
            }
            return ROOM;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}