//<p>给你一个 <code>m x n</code> 的矩阵 <code>matrix</code> 和一个整数 <code>k</code> ，找出并返回矩阵内部矩形区域的不超过 <code>k</code> 的最大数值和。</p>
//
//<p>题目数据保证总会存在一个数值和不超过 <code>k</code> 的矩形区域。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/03/18/sum-grid.jpg" style="width: 255px; height: 176px;" /> 
//<pre>
//<strong>输入：</strong>matrix = [[1,0,1],[0,-2,3]], k = 2
//<strong>输出：</strong>2
//<strong>解释：</strong>蓝色边框圈出来的矩形区域&nbsp;<span><code>[[0, 1], [-2, 3]]</code></span>&nbsp;的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>matrix = [[2,2,-1]], k = 3
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == matrix.length</code></li> 
// <li><code>n == matrix[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 100</code></li> 
// <li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li> 
// <li><code>-10<sup>5</sup> &lt;= k &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>如果行数远大于列数，该如何设计解决方案？</p>
//
//<div><li>👍 430</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//363.矩形区域不超过 K 的最大数值和
//开题时间：2022-11-24 11:18:27
public class MaxSumOfRectangleNoLargerThanK {
    public static void main(String[] args) {
        Solution solution = new MaxSumOfRectangleNoLargerThanK().new Solution();
        System.out.println(solution.maxSumSubmatrix(new int[][]{{2, 2, -1}}, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSumSubmatrixX(int[][] matrix, int k) {
            int m = matrix.length;
            int n = matrix[0].length;

            //prefix sum
            int[][] prefix = new int[m][n];
            for (int c = 0; c < n; c++) {
                prefix[0][c] = matrix[0][c];
                for (int r = 1; r < m; r++)
                    prefix[r][c] = prefix[r - 1][c] + matrix[r][c];
            }

            int min = Integer.MAX_VALUE;
            for (int r1 = 0; r1 < m; r1++)
                for (int r2 = r1; r2 < m; r2++)
                    for (int c2 = 0, c1 = 0, cur = 0; c2 < n; c2++) {
                        cur = Math.max(cur, 0) + prefix[r2][c2] - (r1 > 0 ? prefix[r1 - 1][c2] : 0);
                        if (k >= cur)
                            min = Math.min(min, k - cur);
                    }

            return k - min;
        }

        public int maxSumSubmatrix(int[][] matrix, int k) {
            int m = matrix.length;
            int n = matrix[0].length;

            //m*n
            int[][] prefix = new int[m][n];
            for (int r = 0; r < m; r++) {
                prefix[r][0] = matrix[r][0];
                for (int c = 1; c < n; c++)
                    prefix[r][c] = prefix[r][c - 1] + matrix[r][c];
            }

            //m*m*n
            int[][][] prefix2 = new int[m][m][n];
            for (int r1 = 0; r1 < m; r1++)
                for (int c = 0; c < n; c++) {
                    prefix2[r1][r1][c] = prefix[r1][c];
                    for (int r2 = r1 + 1; r2 < m; r2++)
                        prefix2[r1][r2][c] = prefix2[r1][r2 - 1][c] + prefix[r2][c];
                }

            //(m*n)^2
            int max = Integer.MIN_VALUE;
            for (int r1 = 0; r1 < m; r1++)
                for (int c1 = 0; c1 < n; c1++)
                    for (int r2 = r1; r2 < m; r2++)
                        for (int c2 = c1; c2 < n; c2++) {
                            int sum = prefix2[r1][r2][c2] - (c1 > 0 ? prefix2[r1][r2][c1 - 1] : 0);
                            if (sum <= k && sum > max)
                                max = sum;
                        }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}