//<p>给你一个大小为 <code>m x n</code> 的二进制矩阵 <code>grid</code> 。</p>
//
//<p><strong>岛屿</strong>&nbsp;是由一些相邻的&nbsp;<code>1</code>&nbsp;(代表土地) 构成的组合，这里的「相邻」要求两个 <code>1</code> 必须在 <strong>水平或者竖直的四个方向上 </strong>相邻。你可以假设&nbsp;<code>grid</code> 的四个边缘都被 <code>0</code>（代表水）包围着。</p>
//
//<p>岛屿的面积是岛上值为 <code>1</code> 的单元格的数目。</p>
//
//<p>计算并返回 <code>grid</code> 中最大的岛屿面积。如果没有岛屿，则返回面积为 <code>0</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg" style="width: 500px; height: 310px;" /> 
//<pre>
//<strong>输入：</strong>grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//<strong>输出：</strong>6
//<strong>解释：</strong>答案不应该是 <span><code>11</code></span> ，因为岛屿只能包含水平或垂直这四个方向上的 <span><code>1</code></span> 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[0,0,0,0,0,0,0,0]]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == grid.length</code></li> 
// <li><code>n == grid[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 50</code></li> 
// <li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li> 
//</ul>
//
//<div><li>👍 904</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

//695.岛屿的最大面积
//开题时间：2023-01-02 10:17:27
public class MaxAreaOfIsland {
    public static void main(String[] args) {
        Solution solution = new MaxAreaOfIsland().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int[] DIRS = {1, 0, -1, 0, 1};

        //DFS
        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            for (int i = 0; i < grid.length; i++)
                for (int j = 0; j < grid[0].length; j++)
                    if (grid[i][j] == 1)
                        max = Math.max(max, dfs(grid, i, j));
            return max;
        }

        private int dfs(int[][] arr, int r, int c) {
            int cnt = 1;
            arr[r][c] = 0;
            for (int i = 0; i < 4; i++) {
                int newI = r + DIRS[i];
                int newJ = c + DIRS[i + 1];
                if (0 <= newI && newI < arr.length && 0 <= newJ && newJ < arr[0].length &&
                        arr[newI][newJ] == 1)
                    cnt += dfs(arr, newI, newJ);
            }
            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}