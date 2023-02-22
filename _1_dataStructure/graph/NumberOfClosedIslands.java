//<p>二维矩阵 <code>grid</code>&nbsp;由 <code>0</code>&nbsp;（土地）和 <code>1</code>&nbsp;（水）组成。岛是由最大的4个方向连通的 <code>0</code>&nbsp;组成的群，封闭岛是一个&nbsp;<code>完全</code> 由1包围（左、上、右、下）的岛。</p>
//
//<p>请返回 <em>封闭岛屿</em> 的数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2019/10/31/sample_3_1610.png" style="height: 151px; width: 240px;" /></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
//<strong>输出：</strong>2
//<strong>解释：</strong>
// 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/07/sample_4_1610.png" style="height: 98px; width: 160px;" /></p>
//
//<pre>
//<strong>输入：</strong>grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1,1,1,1,1,1,1],
//&nbsp;            [1,0,0,0,0,0,1],
//&nbsp;            [1,0,1,1,1,0,1],
//&nbsp;            [1,0,1,0,1,0,1],
//&nbsp;            [1,0,1,1,1,0,1],
//&nbsp;            [1,0,0,0,0,0,1],
//             [1,1,1,1,1,1,1]]
//<strong>输出：</strong>2
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= grid.length, grid[0].length &lt;= 100</code></li> 
// <li><code>0 &lt;= grid[i][j] &lt;=1</code></li> 
//</ul>
//
//<div><li>👍 171</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

// 1254.统计封闭岛屿的数目
// 开题时间：2023-01-02 10:32:15
public class NumberOfClosedIslands {
  public static void main(String[] args) {
    Solution solution = new NumberOfClosedIslands().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
  
    // 单 dfs
    public int closedIsland9(int[][] grid) {
      int cnt = 0;
      for (int i = 1; i < grid.length - 1; i++)
        for (int j = 1; j < grid[0].length - 1; j++)
          if (grid[i][j] == 0 && dfs(grid, i, j))
            cnt++;
      return cnt;
    }
  
    private boolean dfs(int[][] arr, int r, int c) {
      boolean ans = r != 0 && r != arr.length - 1 && c != 0 && c != arr[0].length - 1;
      arr[r][c] = 1;
      for (int i = 0; i < 4; i++) {
        int newI = r + DIRS[i];
        int newJ = c + DIRS[i + 1];
        if (0 <= newI && newI < arr.length && 0 <= newJ && newJ < arr[0].length &&
            arr[newI][newJ] == 0)
          ans = ans & dfs(arr, newI, newJ);
      }
      return ans;
    }
  
    // 双 dfs
    public int closedIsland(int[][] grid) {
      int n = grid.length;
      int m = grid[0].length;
      boolean[][] vis = new boolean[n][m];
    
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && !vis[i][j] && grid[i][j] == 0) {
            dfs(grid, i, j, vis);
          }
        }
      }
    
      int cnt = 0;
      for (int i = 1; i < n - 1; i++) {
        for (int j = 1; j < m - 1; j++) {
          if (!vis[i][j] && grid[i][j] == 0) {
            dfs(grid, i, j, vis);
            cnt++;
          }
        }
      }
    
      return cnt;
    }
  
    private void dfs(int[][] arr, int r, int c, boolean[][] vis) {
      vis[r][c] = true;
    
      for (int i = 0; i < 4; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < arr.length && 0 <= nc && nc < arr[0].length &&
            !vis[nr][nc] && arr[nr][nc] == 0)
          dfs(arr, nr, nc, vis);
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}