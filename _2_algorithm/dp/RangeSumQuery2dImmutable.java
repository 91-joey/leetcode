//<p><big><small>给定一个二维矩阵 <code>matrix</code>，</small></big>以下类型的多个请求：</p>
//
//<ul> 
// <li><big><small>计算其子矩形范围内元素的总和，该子矩阵的 <strong>左上角</strong> 为 <code>(row1,&nbsp;col1)</code> ，<strong>右下角</strong> 为 <code>(row2,&nbsp;col2)</code> 。</small></big></li> 
//</ul>
//
//<p>实现 <code>NumMatrix</code> 类：</p>
//
//<ul> 
// <li><code>NumMatrix(int[][] matrix)</code>&nbsp;给定整数矩阵 <code>matrix</code> 进行初始化</li> 
// <li><code>int sumRegion(int row1, int col1, int row2, int col2)</code>&nbsp;返回<big><small> <strong>左上角</strong></small></big><big><small> <code>(row1,&nbsp;col1)</code>&nbsp;、<strong>右下角</strong>&nbsp;<code>(row2,&nbsp;col2)</code></small></big> 所描述的子矩阵的元素 <strong>总和</strong> 。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://pic.leetcode-cn.com/1626332422-wUpUHT-image.png" style="width: 200px;" /></p>
//
//<pre>
//<strong>输入:</strong> 
//["NumMatrix","sumRegion","sumRegion","sumRegion"]
//[[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
//<strong>输出:</strong> 
//[null, 8, 11, 12]
//
//<strong>解释:</strong>
// NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
// numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
// numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
// numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == matrix.length</code></li> 
// <li><code>n == matrix[i].length</code></li> 
// <li><code>1 &lt;= m,&nbsp;n &lt;=&nbsp;200</code>
//  <meta charset="UTF-8" /></li> 
// <li><code>-10<sup>5</sup>&nbsp;&lt;= matrix[i][j] &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= row1 &lt;= row2 &lt; m</code></li> 
// <li><code>0 &lt;= col1 &lt;= col2 &lt; n</code></li> 
// <li>
//  <meta charset="UTF-8" />最多调用 <code>10<sup>4</sup></code> 次&nbsp;<code>sumRegion</code> 方法</li> 
//</ul>
//
//<div><li>👍 451</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 304.二维区域和检索 - 矩阵不可变
// 开题时间：2022-12-16 16:52:02
public class RangeSumQuery2dImmutable {
  public static void main(String[] args) {
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class NumMatrix {
    int[][] prefix;
    
    public NumMatrix(int[][] matrix) {
      int m = matrix.length + 1;
      int n = matrix[0].length + 1;
      
      prefix = new int[m][n];
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
          prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + matrix[i - 1][j - 1];
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
      return prefix[row2 + 1][col2 + 1] + prefix[row1][col1] - prefix[row2 + 1][col1] - prefix[row1][col2 + 1];
    }
  }
  
  /**
   * Your NumMatrix object will be instantiated and called as such:
   * NumMatrix obj = new NumMatrix(matrix);
   * int param_1 = obj.sumRegion(row1,col1,row2,col2);
   */
  // leetcode submit region end(Prohibit modification and deletion)
}