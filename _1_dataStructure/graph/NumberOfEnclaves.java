//<p>给你一个大小为 <code>m x n</code> 的二进制矩阵 <code>grid</code> ，其中 <code>0</code> 表示一个海洋单元格、<code>1</code> 表示一个陆地单元格。</p>
//
//<p>一次 <strong>移动</strong> 是指从一个陆地单元格走到另一个相邻（<strong>上、下、左、右</strong>）的陆地单元格或跨过 <code>grid</code> 的边界。</p>
//
//<p>返回网格中<strong> 无法 </strong>在任意次数的移动中离开网格边界的陆地单元格的数量。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/enclaves1.jpg" style="height: 200px; width: 200px;" /> 
//<pre>
//<strong>输入：</strong>grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
//<strong>输出：</strong>3
//<strong>解释：</strong>有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/enclaves2.jpg" style="height: 200px; width: 200px;" /> 
//<pre>
//<strong>输入：</strong>grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
//<strong>输出：</strong>0
//<strong>解释：</strong>所有 1 都在边界上或可以到达边界。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == grid.length</code></li> 
// <li><code>n == grid[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 500</code></li> 
// <li><code>grid[i][j]</code> 的值为 <code>0</code> 或 <code>1</code></li> 
//</ul>
//
//<div><li>👍 193</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

//1020.飞地的数量
//开题时间：2023-01-03 16:07:09
public class NumberOfEnclaves {
    public static void main(String[] args) {
        Solution solution = new NumberOfEnclaves().new Solution();
        System.out.println(solution.numEnclaves(Tools.to2DIntArray("[[0,1,1,0,0,0,0,1,1,0,0,0],[1,0,1,1,1,0,1,0,1,1,1,0],[1,1,0,1,0,0,1,1,0,1,1,1],[1,0,0,1,1,0,1,0,1,0,1,0],[1,0,0,0,0,1,0,0,1,1,0,1],[1,1,1,0,0,0,1,0,0,1,1,1],[1,1,1,0,0,0,0,1,0,1,0,1],[0,1,1,1,1,0,0,1,1,0,0,0],[0,1,0,1,0,1,0,1,0,0,0,1],[0,0,1,0,1,1,0,0,0,1,1,1]]")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int[] DIRS = {1, 0, -1, 0, 1};

        public int numEnclaves(int[][] grid) {
            int cnt = 0;
            for (int i = 0; i < grid.length; i++)
                for (int j = 0; j < grid[0].length; j++)
                    if (grid[i][j] == 1) {
                        Island island = dfs(grid, i, j);
                        if (island.closed)
                            cnt += island.cnt;
                    }
            return cnt;
        }

        private Island dfs(int[][] arr, int r, int c) {
            boolean closed = r != 0 && r != arr.length - 1 && c != 0 && c != arr[0].length - 1;
            int cnt = 1;
            arr[r][c] = 0;
            for (int i = 0; i < 4; i++) {
                int newI = r + DIRS[i];
                int newJ = c + DIRS[i + 1];
                if (0 <= newI && newI < arr.length && 0 <= newJ && newJ < arr[0].length &&
                        arr[newI][newJ] == 1) {
                    Island island = dfs(arr, newI, newJ);
                    closed = closed & island.closed;
                    cnt += island.cnt;
                }
            }
            return new Island(closed, cnt);
        }
    }

    class Island {
        boolean closed;
        int cnt;

        public Island(boolean closed, int cnt) {
            this.closed = closed;
            this.cnt = cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}