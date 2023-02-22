//<p>你有一个凸的
// <meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;边形，其每个顶点都有一个整数值。给定一个整数数组
// <meta charset="UTF-8" />&nbsp;<code>values</code>&nbsp;，其中
// <meta charset="UTF-8" />&nbsp;<code>values[i]</code>&nbsp;是第 <code>i</code> 个顶点的值（即 <strong>顺时针顺序</strong> ）。</p>
//
//<p>假设将多边形 <strong>剖分</strong>&nbsp;为 <code>n - 2</code>&nbsp;个三角形。对于每个三角形，该三角形的值是顶点标记的<strong>乘积</strong>，三角剖分的分数是进行三角剖分后所有 <code>n - 2</code>&nbsp;个三角形的值之和。</p>
//
//<p>返回 <em>多边形进行三角剖分后可以得到的最低分</em> 。<br /> &nbsp;</p>
//
//<ol> 
//</ol>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/02/25/shape1.jpg" /></p>
//
//<pre>
//<strong>输入：</strong>values = [1,2,3]
//<strong>输出：</strong>6
//<strong>解释：</strong>多边形已经三角化，唯一三角形的分数为 6。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/02/25/shape2.jpg" style="height: 163px; width: 446px;" /></p>
//
//<pre>
//<strong>输入：</strong>values = [3,7,4,5]
//<strong>输出：</strong>144
//<strong>解释：</strong>有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/02/25/shape3.jpg" /></p>
//
//<pre>
//<strong>输入：</strong>values = [1,3,1,4,1,5]
//<strong>输出：</strong>13
//<strong>解释：</strong>最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == values.length</code></li> 
// <li><code>3 &lt;= n &lt;= 50</code></li> 
// <li><code>1 &lt;= values[i] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 126</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 1039.多边形三角剖分的最低得分
// 开题时间：2022-12-22 15:01:58
public class MinimumScoreTriangulationOfPolygon {
  public static void main(String[] args) {
    Solution solution = new MinimumScoreTriangulationOfPolygon().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 区间DP
     *  定义：f[i][j]表示顶点i到顶点j组成的多边形进行三角剖分后的最低分
     *  转移：设底边ij的三角形的顶点为 m ，则把多边形划分成3部分：
     *      f[i][j]=f[i][m]+arr[i]*arr[j]*arr[m]+f[m][j],   i<m<j，i+2<=j
     */
    public int minScoreTriangulation(int[] values) {
      int n = values.length;
      int[][] f = new int[n][n];
      for (int i = n - 3; i >= 0; i--)
        for (int j = i + 2; j < n; j++) {
          f[i][j] = Integer.MAX_VALUE;
          for (int m = i + 1; m < j; m++)
            f[i][j] = Math.min(f[i][j], f[i][m] + values[i] * values[j] * values[m] + f[m][j]);
        }
      return f[0][n - 1];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}