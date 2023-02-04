//<p>给你一个大小为 <code>rows x cols</code> 的矩阵 <code>mat</code>，其中 <code>mat[i][j]</code> 是 <code>0</code> 或 <code>1</code>，请返回 <strong>矩阵&nbsp;<em><code>mat</code></em> 中特殊位置的数目</strong> 。</p>
//
//<p><strong>特殊位置</strong> 定义：如果 <code>mat[i][j] == 1</code> 并且第 <code>i</code> 行和第 <code>j</code> 列中的所有其他元素均为 <code>0</code>（行和列的下标均 <strong>从 0 开始</strong> ），则位置 <code>(i, j)</code> 被称为特殊位置。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>mat = [[1,0,0],
//&nbsp;           [0,0,<strong>1</strong>],
//&nbsp;           [1,0,0]]
//<strong>输出：</strong>1
//<strong>解释：</strong>(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>mat = [[<strong>1</strong>,0,0],
//&nbsp;           [0,<strong>1</strong>,0],
//&nbsp;           [0,0,<strong>1</strong>]]
//<strong>输出：</strong>3
//<strong>解释：</strong>(0,0), (1,1) 和 (2,2) 都是特殊位置
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>mat = [[0,0,0,<strong>1</strong>],
//&nbsp;           [<strong>1</strong>,0,0,0],
//&nbsp;           [0,1,1,0],
//&nbsp;           [0,0,0,0]]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>mat = [[0,0,0,0,0],
//&nbsp;           [<strong>1</strong>,0,0,0,0],
//&nbsp;           [0,<strong>1</strong>,0,0,0],
//&nbsp;           [0,0,<strong>1</strong>,0,0],
//&nbsp;           [0,0,0,1,1]]
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>rows == mat.length</code></li> 
// <li><code>cols == mat[i].length</code></li> 
// <li><code>1 &lt;= rows, cols &lt;= 100</code></li> 
// <li><code>mat[i][j]</code> 是 <code>0</code> 或 <code>1</code></li> 
//</ul>
//
//<div><li>👍 92</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.Arrays;

// 1582.二进制矩阵中的特殊位置
// 开题时间：2022-12-25 12:14:57
public class SpecialPositionsInABinaryMatrix {
  public static void main(String[] args) {
    Solution solution = new SpecialPositionsInABinaryMatrix().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int numSpecial9(int[][] mat) {
      int m = mat.length;
      int n = mat[0].length;
      int[] rows = new int[m];
      for (int i = 0; i < m; i++)
        rows[i] = Arrays.stream(mat[i]).sum();
      
      int cnt = 0;
      out:
      for (int c = 0; c < n; c++) {
        int row = -1;
        for (int r = 0; r < m; r++)
          if (1 == mat[r][c])
            if (row == -1)
              row = r;
            else
              continue out;
        if (row != -1 && rows[row] == 1)
          cnt++;
      }
      
      return cnt;
    }
    
    /*
     * ☆☆☆☆ 预处理行列的 1 个数
     * 遍历矩阵，计数 元素值、行列中 1 个数均为 1 的元素
     */
    public int numSpecial8(int[][] mat) {
      int m = mat.length;
      int n = mat[0].length;
      int[] rows = new int[m];
      int[] cols = new int[n];
      for (int i = 0; i < m; i++)
        rows[i] = Arrays.stream(mat[i]).sum();
      for (int i = 0; i < n; i++) {
        int finalI = i;
        cols[i] = Arrays.stream(mat).mapToInt(arr -> arr[finalI]).sum();
      }
      
      int cnt = 0;
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          if (mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1)
            cnt++;
      
      return cnt;
    }
    
    /*
     * 官解：
     * 列标记值：表示每一列的所有值为 1 所在行所有 1 的个数之和 之和
     * 当且仅当列标记值为 1 时，为特殊位置
     */
    public int numSpecial7(int[][] mat) {
      int m = mat.length;
      int n = mat[0].length;
      for (int i = 0; i < m; i++) {
        int cnt = 0;
        for (int j = 0; j < n; j++)
          cnt += mat[i][j];
        if (i == 0)
          cnt--;
        if (cnt > 0)
          for (int j = 0; j < n; j++)
            if (mat[i][j] == 1)
              mat[0][j] += cnt;
      }
      
      return Arrays.stream(mat[0])
          .filter(x -> x == 1)
          .sum();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}