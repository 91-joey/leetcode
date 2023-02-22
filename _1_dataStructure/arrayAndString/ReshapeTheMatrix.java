//<p>在 MATLAB 中，有一个非常有用的函数 <code>reshape</code> ，它可以将一个&nbsp;<code>m x n</code> 矩阵重塑为另一个大小不同（<code>r x c</code>）的新矩阵，但保留其原始数据。</p>
//
//<p>给你一个由二维数组 <code>mat</code> 表示的&nbsp;<code>m x n</code> 矩阵，以及两个正整数 <code>r</code> 和 <code>c</code> ，分别表示想要的重构的矩阵的行数和列数。</p>
//
//<p>重构后的矩阵需要将原始矩阵的所有元素以相同的<strong> 行遍历顺序 </strong>填充。</p>
//
//<p>如果具有给定参数的 <code>reshape</code> 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/04/24/reshape1-grid.jpg" style="width: 613px; height: 173px;" /> 
//<pre>
//<strong>输入：</strong>mat = [[1,2],[3,4]], r = 1, c = 4
//<strong>输出：</strong>[[1,2,3,4]]
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/04/24/reshape2-grid.jpg" style="width: 453px; height: 173px;" /> 
//<pre>
//<strong>输入：</strong>mat = [[1,2],[3,4]], r = 2, c = 4
//<strong>输出：</strong>[[1,2],[3,4]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == mat.length</code></li> 
// <li><code>n == mat[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 100</code></li> 
// <li><code>-1000 &lt;= mat[i][j] &lt;= 1000</code></li> 
// <li><code>1 &lt;= r, c &lt;= 300</code></li> 
//</ul>
//
//<div><li>👍 355</li><li>👎 0</li></div>
package _1_dataStructure.arrayAndString;

// 566.重塑矩阵
// 开题时间：2022-12-01 11:53:07
public class ReshapeTheMatrix {
  public static void main(String[] args) {
    Solution solution = new ReshapeTheMatrix().new Solution();
    System.out.println(solution.matrixReshape(new int[][]{{1, 2}, {3, 4}}, 1, 4));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
      int m = mat.length;
      int n = mat[0].length;
      int size = m * n;
      if (size != r * c)
        return mat;
      
      int[][] reshaped = new int[r][c];
      for (int i = 0; i < size; i++)
        reshaped[i / c][i % c] = mat[i / n][i % n];
      return reshaped;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}