//<p>给你两个非负整数数组&nbsp;<code>rowSum</code> 和&nbsp;<code>colSum</code>&nbsp;，其中&nbsp;<code>rowSum[i]</code>&nbsp;是二维矩阵中第 <code>i</code>&nbsp;行元素的和， <code>colSum[j]</code>&nbsp;是第 <code>j</code>&nbsp;列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。</p>
//
//<p>请找到大小为&nbsp;<code>rowSum.length x colSum.length</code>&nbsp;的任意 <strong>非负整数</strong>&nbsp;矩阵，且该矩阵满足&nbsp;<code>rowSum</code> 和&nbsp;<code>colSum</code>&nbsp;的要求。</p>
//
//<p>请你返回任意一个满足题目要求的二维矩阵，题目保证存在 <strong>至少一个</strong>&nbsp;可行矩阵。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>rowSum = [3,8], colSum = [4,7]
//<strong>输出：</strong>[[3,0],
//      [1,7]]
//<strong>解释：</strong>
// 第 0 行：3 + 0 = 3 == rowSum[0]
// 第 1 行：1 + 7 = 8 == rowSum[1]
// 第 0 列：3 + 1 = 4 == colSum[0]
// 第 1 列：0 + 7 = 7 == colSum[1]
// 行和列的和都满足题目要求，且所有矩阵元素都是非负的。
// 另一个可行的矩阵为：[[1,2],
//                  [3,5]]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>rowSum = [5,7,10], colSum = [8,6,8]
//<strong>输出：</strong>[[0,5,0],
//      [6,1,0],
//      [2,0,8]]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>rowSum = [14,9], colSum = [6,9,8]
//<strong>输出：</strong>[[0,9,5],
//      [6,0,3]]
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>rowSum = [1,0], colSum = [1]
//<strong>输出：</strong>[[1],
//      [0]]
//</pre>
//
//<p><strong>示例 5：</strong></p>
//
//<pre>
//<strong>输入：</strong>rowSum = [0], colSum = [0]
//<strong>输出：</strong>[[0]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= rowSum.length, colSum.length &lt;= 500</code></li> 
// <li><code>0 &lt;= rowSum[i], colSum[i] &lt;= 10<sup>8</sup></code></li> 
// <li><code>sum(rows) == sum(columns)</code></li> 
//</ul>
//
//<div><li>👍 55</li><li>👎 0</li></div>
package _2_algorithm.greedy;

// 1605.给定行和列的和求可行矩阵
// 开题时间：2023-01-25 17:55:11
public class FindValidMatrixGivenRowAndColumnSums {
  public static void main(String[] args) {
    Solution solution = new FindValidMatrixGivenRowAndColumnSums().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 贪心
     * 双重循环：将 ans[i][j] 设为 min(rowSum[i], colSum[j])，并更新 rowSum[i]、colSum[j]
     * 最后总能保证 rowSum[i] = colSum[j] = 0，即该矩阵满足要求
     */
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
      int m = rowSum.length;
      int n = colSum.length;
      int[][] ans = new int[m][n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          ans[i][j] = Math.min(rowSum[i], colSum[j]);
          rowSum[i] -= ans[i][j];
          colSum[j] -= ans[i][j];
        }
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}