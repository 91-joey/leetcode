//<p>给你一个 <code>m</code> 行 <code>n</code> 列的矩阵&nbsp;<code>matrix</code> ，请按照 <strong>顺时针螺旋顺序</strong> ，返回矩阵中的所有元素。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg" style="width: 242px; height: 242px;" /> 
//<pre>
//<strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
//<strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral.jpg" style="width: 322px; height: 242px;" /> 
//<pre>
//<strong>输入：</strong>matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//<strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == matrix.length</code></li> 
// <li><code>n == matrix[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 10</code></li> 
// <li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 1275</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.simulation;

import java.util.ArrayList;
import java.util.List;

// 54.螺旋矩阵
// 开题时间：2022-12-22 12:45:53
public class SpiralMatrix {
  public static void main(String[] args) {
    Solution solution = new SpiralMatrix().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
      int m = matrix.length;
      int n = matrix[0].length;
      ArrayList<Integer> ans = new ArrayList<>(m * n);
      
      for (int l = 0, r = n - 1, t = 0, b = m - 1, idx = 0; ; ) {
        for (int i = l; i <= r; i++) ans.add(matrix[t][i]);
        if (++t > b) break;
        for (int i = t; i <= b; i++) ans.add(matrix[i][r]);
        if (l > --r) break;
        for (int i = r; i >= l; i--) ans.add(matrix[b][i]);
        if (t > --b) break;
        for (int i = b; i >= t; i--) ans.add(matrix[i][l]);
        if (++l > r) break;
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}