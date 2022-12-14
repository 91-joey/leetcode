//<p>在一个由 <code>'0'</code> 和 <code>'1'</code> 组成的二维矩阵内，找到只包含 <code>'1'</code> 的最大正方形，并返回其面积。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/max1grid.jpg" style="width: 400px; height: 319px;" /> 
//<pre>
//<strong>输入：</strong>matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
//<strong>输出：</strong>4
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/max2grid.jpg" style="width: 165px; height: 165px;" /> 
//<pre>
//<strong>输入：</strong>matrix = [["0","1"],["1","0"]]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>matrix = [["0"]]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == matrix.length</code></li> 
// <li><code>n == matrix[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 300</code></li> 
// <li><code>matrix[i][j]</code> 为 <code>'0'</code> 或 <code>'1'</code></li> 
//</ul>
//
//<div><li>👍 1334</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//221.最大正方形
//开题时间：2022-12-14 10:16:37
public class MaximalSquare {
    public static void main(String[] args) {
        Solution solution = new MaximalSquare().new Solution();
        System.out.println(solution.maximalSquare(new char[][]{{'1'}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //前缀和
        public int maximalSquare9(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] prefix = new int[m][n + 1];
            for (int i = 0; i < m; i++)
                for (int j = 1; j < n + 1; j++)
                    prefix[i][j] = prefix[i][j - 1] + matrix[i][j - 1] - '0';

            int[][][] multi = new int[m + 1][m + 1][n + 1];
            for (int i = 1; i < m + 1; i++)
                for (int j = i; j < m + 1; j++)
                    for (int k = 1; k < n + 1; k++)
                        multi[i][j][k] = multi[i][j - 1][k] + prefix[j - 1][k];

            for (int len = Math.min(m, n); len > 0; len--)
                for (int i = 0; i + len <= m; i++)
                    for (int j = 0; j + len <= n; j++)
                        if (len * len == multi[i + 1][i + len][j + len] - multi[i + 1][i + len][j])
                            return len * len;

            return 0;
        }

        /*
         * DP
         * f[i][j] 表示以matrix[i - 1][j - 1]为右下角最大正方形边长
         *      matrix[i - 1][j - 1] == '1'时，f[i][j] = 1 + min(f[i - 1][j - 1], f[i - 1][j], f[i][j - 1])
         *      否则为 0
         * 答案为max{f[i][j]} ^ 2
         */
        public int maximalSquare8(char[][] matrix) {
            int m = matrix.length + 1;
            int n = matrix[0].length + 1;
            int[][] f = new int[m][n];

            int max = 0;
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    if (matrix[i - 1][j - 1] == '1') {
                        f[i][j] = 1 + min(f[i - 1][j - 1], f[i - 1][j], f[i][j - 1]);
                        max = Math.max(max, f[i][j]);
                    }

            return max * max;
        }

        //☆☆☆☆☆ DP + 滚动数组（右上角用单独的一个变量维护）
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length + 1;
            int n = matrix[0].length + 1;
            int[] f = new int[n];

            int max = 0;
            for (int i = 1; i < m; i++) {
                int leftUp = 0;
                for (int j = 1; j < n; j++) {
                    int tmp = f[j];
                    if (matrix[i - 1][j - 1] == '1') {
                        f[j] = 1 + min(leftUp, f[j - 1], f[j]);
                        max = Math.max(max, f[j]);
                    } else
                        f[j] = 0;
                    leftUp = tmp;
                }
            }

            return max * max;
        }

        public static int min(int... arr) {
            int min = Integer.MAX_VALUE;
            for (int x : arr) if (min > x) min = x;
            return min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}