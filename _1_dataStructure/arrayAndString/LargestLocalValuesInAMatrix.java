//<p>给你一个大小为 <code>n x n</code> 的整数矩阵 <code>grid</code> 。</p>
//
//<p>生成一个大小为&nbsp;<code>(n - 2) x (n - 2)</code> 的整数矩阵&nbsp; <code>maxLocal</code> ，并满足：</p>
//
//<ul> 
// <li><code>maxLocal[i][j]</code> 等于 <code>grid</code> 中以 <code>i + 1</code> 行和 <code>j + 1</code> 列为中心的 <code>3 x 3</code> 矩阵中的 <strong>最大值</strong> 。</li> 
//</ul>
//
//<p>换句话说，我们希望找出 <code>grid</code> 中每个&nbsp;<code>3 x 3</code> 矩阵中的最大值。</p>
//
//<p>返回生成的矩阵。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2022/06/21/ex1.png" style="width: 371px; height: 210px;" /></p>
//
//<pre>
//<strong>输入：</strong>grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
//<strong>输出：</strong>[[9,9],[8,6]]
//<strong>解释：</strong>原矩阵和生成的矩阵如上图所示。
//注意，生成的矩阵中，每个值都对应 grid 中一个相接的 3 x 3 矩阵的最大值。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2022/07/02/ex2new2.png" style="width: 436px; height: 240px;" /></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
//<strong>输出：</strong>[[2,2,2],[2,2,2],[2,2,2]]
//<strong>解释：</strong>注意，2 包含在 grid 中每个 3 x 3 的矩阵中。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == grid.length == grid[i].length</code></li> 
// <li><code>3 &lt;= n &lt;= 100</code></li> 
// <li><code>1 &lt;= grid[i][j] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 11</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.Arrays;
import java.util.LinkedList;

//2373.矩阵中的局部最大值
//开题时间：2022-11-08 08:39:31
public class LargestLocalValuesInAMatrix {
    public static void main(String[] args) {
        Solution solution = new LargestLocalValuesInAMatrix().new Solution();
        System.out.println(Arrays.deepToString(solution.largestLocal(new int[][]{
                {20, 8, 20, 6, 16, 16, 7, 16, 8, 10},
                {12, 15, 13, 10, 20, 9, 6, 18, 17, 6},
                {12, 4, 10, 13, 20, 11, 15, 5, 17, 1},
                {7, 10, 14, 14, 16, 5, 1, 7, 3, 11},
                {16, 2, 9, 15, 9, 8, 6, 1, 7, 15},
                {18, 15, 18, 8, 12, 17, 19, 7, 7, 8},
                {19, 11, 15, 16, 1, 3, 7, 4, 7, 11},
                {11, 6, 5, 14, 12, 18, 3, 20, 14, 6},
                {4, 4, 19, 6, 17, 12, 8, 8, 18, 8},
                {19, 15, 14, 11, 11, 13, 12, 6, 16, 19},
        })));
//        System.out.println(Arrays.deepToString(solution.largestLocal(new int[][]{{9, 9, 8, 1}, {5, 6, 2, 6}, {8, 2, 6, 4}, {6, 2, 2, 2}})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //单调递减队列
        public int[][] largestLocal(int[][] grid) {
            int n = grid.length;
            int[][] maxLocal = new int[n - 2][n - 2];

            LinkedList<int[]> q = new LinkedList<>();
            for (int i = 0, a = 0, b = 0; i < n - 2; i++) {
                for (int y = 0; y <= 2; y++) {
                    for (int x = i, max = 0; x <= i + 2; x++) {
                        if (grid[x][y] > max) {
                            a = x;
                            b = y;
                            max = grid[x][y];
                        }
                    }
                    while (!q.isEmpty() && grid[a][b] >= grid[q.peekLast()[0]][q.peekLast()[1]])
                        q.pollLast();
                    q.offer(new int[]{a, b});
                }
                int[] peek = q.peek();
                maxLocal[i][0] = grid[peek[0]][peek[1]];

                for (int j = 1; j < n - 2; j++) {
                    if (q.peek()[1] == j - 1)
                        q.poll();

                    int col = j + 2;
                    for (int x = i, max = 0; x <= i + 2; x++) {
                        if (grid[x][col] > max) {
                            a = x;
                            max = grid[x][col];
                        }
                    }
                    while (!q.isEmpty() && grid[a][col] >= grid[q.peekLast()[0]][q.peekLast()[1]])
                        q.pollLast();
                    q.offer(new int[]{a, col});

                    peek = q.peek();
                    maxLocal[i][j] = grid[peek[0]][peek[1]];
                }
                q.clear();
            }

            return maxLocal;
        }

        public int[][] largestLocal9(int[][] grid) {
            int n = grid.length;
            int[][] maxLocal = new int[n - 2][n - 2];

            for (int i = 0; i < n - 2; i++) {
                for (int j = 0; j < n - 2; j++) {
                    int max = 0;
                    for (int x = i; x <= i + 2; x++)
                        for (int y = j; y <= j + 2; y++)
                            max = Math.max(max, grid[x][y]);
                    maxLocal[i][j] = max;
                }
            }

            return maxLocal;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}