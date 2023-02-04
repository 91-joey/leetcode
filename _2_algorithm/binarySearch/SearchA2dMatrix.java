//<p>编写一个高效的算法来判断&nbsp;<code>m x n</code>&nbsp;矩阵中，是否存在一个目标值。该矩阵具有如下特性：</p>
//
//<ul> 
// <li>每行中的整数从左到右按升序排列。</li> 
// <li>每行的第一个整数大于前一行的最后一个整数。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/mat.jpg" style="width: 322px; height: 242px;" /> 
//<pre>
//<strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/mat2.jpg" style="width: 322px; height: 242px;" /> 
//<pre>
//<strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == matrix.length</code></li> 
// <li><code>n == matrix[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 100</code></li> 
// <li><code>-10<sup>4</sup> &lt;= matrix[i][j], target &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 742</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

// 74.搜索二维矩阵
// 开题时间：2022-11-27 21:40:51
public class SearchA2dMatrix {
  public static void main(String[] args) {
    Solution solution = new SearchA2dMatrix().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 2D坐标转换为1D坐标
    public boolean searchMatrix(int[][] matrix, int target) {
      int m = matrix.length;
      int n = matrix[0].length;
      
      int l = 0, r = m * n - 1;
      while (l <= r) {
        int mid = ((r - l) >> 1) + l;
        int val = matrix[mid / n][mid % n];
        if (target == val)
          return true;
        else if (target < val)
          r = mid - 1;
        else
          l = mid + 1;
      }
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}