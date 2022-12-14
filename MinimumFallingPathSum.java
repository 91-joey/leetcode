//<p>给你一个 <code>n x n</code> 的<strong> 方形 </strong>整数数组&nbsp;<code>matrix</code> ，请你找出并返回通过 <code>matrix</code> 的<strong>下降路径</strong><em> </em>的<strong> </strong><strong>最小和</strong> 。</p>
//
//<p><strong>下降路径</strong> 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 <code>(row, col)</code> 的下一个元素应当是 <code>(row + 1, col - 1)</code>、<code>(row + 1, col)</code> 或者 <code>(row + 1, col + 1)</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/11/03/failing1-grid.jpg" style="height: 500px; width: 499px;" /></p>
//
//<pre>
//<strong>输入：</strong>matrix = [[2,1,3],[6,5,4],[7,8,9]]
//<strong>输出：</strong>13
//<strong>解释：</strong>如图所示，为和最小的两条下降路径
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/11/03/failing2-grid.jpg" style="height: 365px; width: 164px;" /></p>
//
//<pre>
//<strong>输入：</strong>matrix = [[-19,57],[-40,-5]]
//<strong>输出：</strong>-59
//<strong>解释：</strong>如图所示，为和最小的下降路径
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == matrix.length == matrix[i].length</code></li> 
// <li><code>1 &lt;= n &lt;= 100</code></li> 
// <li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 204</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//931.下降路径最小和
//开题时间：2022-12-14 12:08:48
public class MinimumFallingPathSum {
    public static void main(String[] args) {
        Solution solution = new MinimumFallingPathSum().new Solution();
        System.out.println(solution.minFallingPathSum(new int[][]{{-48}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //DP（原始）
        public int minFallingPathSum9(int[][] matrix) {
            if (matrix.length == 1)
                return matrix[0][0];

            int n = matrix.length + 1;
            int[][] f = new int[n][n - 1];

            for (int i = 1; i < n; i++) {
                f[i][0] = matrix[i - 1][0] + Math.min(f[i - 1][0], f[i - 1][1]);
                for (int j = 1; j < n - 2; j++)
                    f[i][j] = matrix[i - 1][j] + min(f[i - 1][j - 1], f[i - 1][j], f[i - 1][j + 1]);
                f[i][n - 2] = matrix[i - 1][n - 2] + Math.min(f[i - 1][n - 3], f[i - 1][n - 2]);
            }

            return Arrays.stream(f[n - 1]).min().getAsInt();
        }

        //DP（初始化首行元素）
        public int minFallingPathSum8(int[][] matrix) {
            int n = matrix.length;
            int[][] f = new int[n][n];
            f[0] = matrix[0];

            for (int i = 1; i < n; i++) {
                f[i][0] = matrix[i][0] + Math.min(f[i - 1][0], f[i - 1][1]);
                for (int j = 1; j < n - 1; j++)
                    f[i][j] = matrix[i][j] + min(f[i - 1][j - 1], f[i - 1][j], f[i - 1][j + 1]);
                f[i][n - 1] = matrix[i][n - 1] + Math.min(f[i - 1][n - 2], f[i - 1][n - 1]);
            }

            return Arrays.stream(f[n - 1]).min().getAsInt();
        }

        //☆☆☆☆☆ DP（初始化首列、尾列元素）
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length + 1;
            int[][] f = new int[n][n + 1];
            for (int i = 0; i < n; i++)
                f[i][0] = f[i][n] = Integer.MAX_VALUE;

            for (int i = 1; i < n; i++)
                for (int j = 1; j < n; j++)
                    f[i][j] = matrix[i - 1][j - 1] + min(f[i - 1][j - 1], f[i - 1][j], f[i - 1][j + 1]);

            return Arrays.stream(f[n - 1]).min().getAsInt();
        }

        public static int min(int... arr) {
            int min = Integer.MAX_VALUE;
            for (int x : arr) if (min > x) min = x;
            return min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}