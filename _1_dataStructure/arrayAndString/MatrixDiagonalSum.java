//<p>给你一个正方形矩阵 <code>mat</code>，请你返回矩阵对角线元素的和。</p>
//
//<p>请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp; 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2020/08/14/sample_1911.png" style="height:174px; width:336px" /></p>
//
//<pre>
//<strong>输入：</strong>mat = [[<strong>1</strong>,2,<strong>3</strong>],
//&nbsp;           [4,<strong>5</strong>,6],
//&nbsp;           [<strong>7</strong>,8,<strong>9</strong>]]
//<strong>输出：</strong>25
//<strong>解释：</strong>对角线的和为：1 + 5 + 9 + 3 + 7 = 25
//请注意，元素 mat[1][1] = 5 只会被计算一次。
//</pre>
//
//<p><strong>示例&nbsp; 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>mat = [[<strong>1</strong>,1,1,<strong>1</strong>],
//&nbsp;           [1,<strong>1</strong>,<strong>1</strong>,1],
//&nbsp;           [1,<strong>1</strong>,<strong>1</strong>,1],
//&nbsp;           [<strong>1</strong>,1,1,<strong>1</strong>]]
//<strong>输出：</strong>8
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>mat = [[<strong>5</strong>]]
//<strong>输出：</strong>5
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == mat.length == mat[i].length</code></li> 
// <li><code>1 &lt;= n &lt;= 100</code></li> 
// <li><code>1 &lt;= mat[i][j] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 63</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

//1572.矩阵对角线元素的和
//开题时间：2022-12-07 09:12:55
public class MatrixDiagonalSum {
    public static void main(String[] args) {
        Solution solution = new MatrixDiagonalSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int diagonalSum9(int[][] mat) {
            int sum = 0;
            int n = mat.length;
            for (int i = 0, j = n - 1; i < n; i++, j--)
                sum += mat[i][i] + mat[j][n - 1 - j];
            return sum - (n % 2 == 1 ? mat[n / 2][n / 2] : 0);
        }

        public int diagonalSum(int[][] mat) {
            int sum = 0;
            int n = mat.length;

            for (int i = 0; i < n; i++)
                sum += mat[i][i] + mat[i][n - 1 - i];

            int mid = n / 2;
            return sum - mat[mid][mid] * (n & 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}