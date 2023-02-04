//<p>给定一个包含非负整数的 <code><em>m</em>&nbsp;x&nbsp;<em>n</em></code>&nbsp;网格&nbsp;<code>grid</code> ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。</p>
//
//<p><strong>说明：</strong>每次只能向下或者向右移动一步。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/11/05/minpath.jpg" style="width: 242px; height: 242px;" /> 
//<pre>
//<strong>输入：</strong>grid = [[1,3,1],[1,5,1],[4,2,1]]
//<strong>输出：</strong>7
//<strong>解释：</strong>因为路径 1→3→1→1→1 的总和最小。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1,2,3],[4,5,6]]
//<strong>输出：</strong>12
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == grid.length</code></li> 
// <li><code>n == grid[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 200</code></li> 
// <li><code>0 &lt;= grid[i][j] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 1402</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import org.example.leetcode.problems._3_common.tool.Tools;

// 64.最小路径和
// 开题时间：2022-12-13 16:14:13
public class MinimumPathSum {
  public static void main(String[] args) {
    Solution solution = new MinimumPathSum().new Solution();
    System.out.println(solution.minPathSum(Tools.to2DIntArray("[[1,3,1],[1,5,1],[4,2,1]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int minPathSum9(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      int[][] f = new int[m][n];
      
      f[0][0] = grid[0][0];
      for (int i = 1; i < m; i++) f[i][0] = f[i - 1][0] + grid[i][0];
      for (int j = 1; j < n; j++) f[0][j] = f[0][j - 1] + grid[0][j];
      
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
          f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
      
      return f[m - 1][n - 1];
    }
    
    // DP+滚动数组
    public int minPathSum8(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      int[] f = new int[n];
      
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          f[j] = i <= 0 && j <= 0 ?
              grid[i][j] :
              Math.min(i <= 0 ? Integer.MAX_VALUE : f[j], j <= 0 ? Integer.MAX_VALUE : f[j - 1]) + grid[i][j];
      
      return f[n - 1];
    }
    
    //☆☆☆☆☆ 原地DP
    public int minPathSum(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      
      for (int i = 1; i < m; i++) grid[i][0] = grid[i - 1][0] + grid[i][0];
      for (int j = 1; j < n; j++) grid[0][j] = grid[0][j - 1] + grid[0][j];
      
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
          grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
      
      return grid[m - 1][n - 1];
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}