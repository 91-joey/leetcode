//<p>给你一个&nbsp;<code>m x n</code>&nbsp;的矩阵&nbsp;<code>mat</code>&nbsp;和一个整数 <code>k</code> ，请你返回一个矩阵&nbsp;<code>answer</code>&nbsp;，其中每个&nbsp;<code>answer[i][j]</code>&nbsp;是所有满足下述条件的元素&nbsp;<code>mat[r][c]</code> 的和：&nbsp;</p>
//
//<ul> 
// <li><code>i - k &lt;= r &lt;= i + k, </code></li> 
// <li><code>j - k &lt;= c &lt;= j + k</code> 且</li> 
// <li><code>(r, c)</code>&nbsp;在矩阵内。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
//<strong>输出：</strong>[[12,21,16],[27,45,33],[24,39,28]]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
//<strong>输出：</strong>[[45,45,45],[45,45,45],[45,45,45]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m ==&nbsp;mat.length</code></li> 
// <li><code>n ==&nbsp;mat[i].length</code></li> 
// <li><code>1 &lt;= m, n, k &lt;= 100</code></li> 
// <li><code>1 &lt;= mat[i][j] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 150</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;

//1314.矩阵区域和
//开题时间：2022-12-19 11:48:57
public class MatrixBlockSum {
    public static void main(String[] args) {
        Solution solution = new MatrixBlockSum().new Solution();
        System.out.println(Arrays.deepToString(solution.matrixBlockSum(Tools.to2DIntArray("[[1,2,3],[4,5,6],[7,8,9]]"), 1)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] matrixBlockSum(int[][] mat, int k) {
            int m = mat.length + 1;
            int n = mat[0].length + 1;

            int[][] prefix = new int[m][n];
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + mat[i - 1][j - 1];

            int[][] ans = new int[m - 1][n - 1];
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    int r1 = Math.max(i - k, 0);
                    int c1 = Math.max(j - k, 0);
                    int r2 = Math.min(i + k + 1, m - 1);
                    int c2 = Math.min(j + k + 1, n - 1);
                    ans[i][j] = prefix[r2][c2] - prefix[r2][c1] - prefix[r1][c2] + prefix[r1][c1];
                }
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}