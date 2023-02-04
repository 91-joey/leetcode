//<p>输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
//<strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>matrix =&nbsp;[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//<strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>限制：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= matrix.length &lt;= 100</code></li> 
// <li><code>0 &lt;= matrix[i].length&nbsp;&lt;= 100</code></li> 
//</ul>
//
//<p>注意：本题与主站 54 题相同：<a href="https://leetcode-cn.com/problems/spiral-matrix/">https://leetcode-cn.com/problems/spiral-matrix/</a></p>
//
//<div><li>👍 479</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.simulation;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;

// 剑指 Offer 29.顺时针打印矩阵
// 开题时间：2022-12-22 09:13:44
public class ShunShiZhenDaYinJuZhenLcof {
  public static void main(String[] args) {
    Solution solution = new ShunShiZhenDaYinJuZhenLcof().new Solution();
    System.out.println(Arrays.toString(solution.spiralOrder(Tools.to2DIntArray("[[1,2,3],[4,5,6],[7,8,9]]"))));
    //        System.out.println(Arrays.toString(solution.spiralOrder(Tools.to2DIntArray("[[7],[9],[6]]"))));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] spiralOrder(int[][] matrix) {
      int m = matrix.length;
      if (m == 0)
        return new int[]{};
      int n = matrix[0].length;
      int[] ans = new int[m * n];
      
      for (int idx = 0, i = 0, j = -1, t = 0, l = 0, b = m - 1, r = n - 1; i <= b && j < r; ) {
        while (j < r) ans[idx++] = matrix[i][++j];
        if (i >= b) break;
        while (i < b) ans[idx++] = matrix[++i][j];
        if (l >= j) break;
        while (l < j) ans[idx++] = matrix[i][--j];
        while (t + 1 < i) ans[idx++] = matrix[--i][j];
        t++;
        l++;
        b--;
        r--;
      }
      
      return ans;
    }
    
    /*
     * ☆☆☆☆☆ 模拟
     *  1. 根据边界打印
     *  2. 边界向内收缩
     *  3. 是否打印完毕
     */
    public int[] spiralOrder8(int[][] matrix) {
      int m = matrix.length;
      if (m == 0)
        return new int[]{};
      int n = matrix[0].length;
      int[] ans = new int[m * n];
      
      for (int l = 0, r = n - 1, t = 0, b = m - 1, idx = 0; ; ) {
        for (int i = l; i <= r; i++) ans[idx++] = matrix[t][i];
        if (++t > b) break;
        for (int i = t; i <= b; i++) ans[idx++] = matrix[i][r];
        if (l > --r) break;
        for (int i = r; i >= l; i--) ans[idx++] = matrix[b][i];
        if (t > --b) break;
        for (int i = b; i >= t; i--) ans[idx++] = matrix[i][l];
        if (++l > r) break;
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}