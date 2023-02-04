//<p>有一个 <code>m × n</code> 的矩形岛屿，与 <strong>太平洋</strong> 和 <strong>大西洋</strong> 相邻。&nbsp;<strong>“太平洋”&nbsp;</strong>处于大陆的左边界和上边界，而 <strong>“大西洋”</strong> 处于大陆的右边界和下边界。</p>
//
//<p>这个岛被分割成一个由若干方形单元格组成的网格。给定一个 <code>m x n</code> 的整数矩阵&nbsp;<code>heights</code>&nbsp;，&nbsp;<code>heights[r][c]</code>&nbsp;表示坐标 <code>(r, c)</code> 上单元格 <strong>高于海平面的高度</strong> 。</p>
//
//<p>岛上雨水较多，如果相邻单元格的高度 <strong>小于或等于</strong> 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。</p>
//
//<p>返回网格坐标 <code>result</code>&nbsp;的 <strong>2D 列表</strong> ，其中&nbsp;<code>result[i] = [r<sub>i</sub>, c<sub>i</sub>]</code>&nbsp;表示雨水从单元格 <code>(ri, ci)</code> 流动 <strong>既可流向太平洋也可流向大西洋</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/06/08/waterflow-grid.jpg" /></p>
//
//<pre>
//<strong>输入:</strong> heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
//<strong>输出:</strong> [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入:</strong> heights = [[2,1],[1,2]]
//<strong>输出:</strong> [[0,0],[0,1],[1,0],[1,1]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == heights.length</code></li> 
// <li><code>n == heights[r].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 200</code></li> 
// <li><code>0 &lt;= heights[r][c] &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><li>👍 559</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// 417.太平洋大西洋水流问题
// 开题时间：2023-01-04 09:21:41
public class PacificAtlanticWaterFlow {
  public static void main(String[] args) {
    Solution solution = new PacificAtlanticWaterFlow().new Solution();
    System.out.println(solution.pacificAtlantic(Tools.to2DIntArray("[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    
    // 暴力DFS (m*n)^2
    public List<List<Integer>> pacificAtlantic9(int[][] heights) {
      ArrayList<List<Integer>> ans = new ArrayList<>();
      int m = heights.length;
      int n = heights[0].length;
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++) {
          boolean[] pa = dfs(heights, i, j, new boolean[m][n]);
          if (pa[0] && pa[1])
            ans.add(List.of(i, j));
        }
      return ans;
    }
    
    /*
     * ☆☆☆☆☆ 逆向思维 + DFS：水往高处流   m*n
     * 分别求出太平洋和大西洋的水所能流到（逆向）的单元格 canReachPacific、canReachAtlantic
     * 两者的交集，即为既可流向（正向）太平洋也可流向（正向）大西洋的所有单元格
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
      int m = heights.length;
      int n = heights[0].length;
      
      boolean[][] canReachPacific = new boolean[m][n];
      boolean[][] canReachAtlantic = new boolean[m][n];
      for (int i = 0; i < m; i++) {
        contraFlow(heights, i, 0, canReachPacific);
        contraFlow(heights, i, n - 1, canReachAtlantic);
      }
      for (int j = 0; j < n; j++) {
        contraFlow(heights, 0, j, canReachPacific);
        contraFlow(heights, m - 1, j, canReachAtlantic);
      }
      
      ArrayList<List<Integer>> ans = new ArrayList<>();
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          if (canReachPacific[i][j] && canReachAtlantic[i][j])
            ans.add(List.of(i, j));
      
      return ans;
    }
    
    private void contraFlow(int[][] arr, int r, int c, boolean[][] canReach) {
      canReach[r][c] = true;
      for (int i = 0; i < 4; i++) {
        int newI = r + DIRS[i];
        int newJ = c + DIRS[i + 1];
        if (0 <= newI && newI < arr.length && 0 <= newJ && newJ < arr[0].length &&
            !canReach[newI][newJ] && arr[newI][newJ] >= arr[r][c])
          contraFlow(arr, newI, newJ, canReach);
      }
    }
    
    private void dfs(int[][] arr, int r, int c, Set<List<Integer>> set, boolean[][] vis) {
      vis[r][c] = true;
      set.add(List.of(r, c));
      for (int i = 0; i < 4; i++) {
        int newI = r + DIRS[i];
        int newJ = c + DIRS[i + 1];
        if (0 <= newI && newI < arr.length && 0 <= newJ && newJ < arr[0].length &&
            !vis[newI][newJ] && arr[newI][newJ] >= arr[r][c])
          dfs(arr, newI, newJ, set, vis);
      }
    }
    
    private boolean[] dfs(int[][] arr, int r, int c, boolean[][] vis) {
      boolean p = c == 0 || r == 0;
      boolean a = c == arr[0].length - 1 || r == arr.length - 1;
      vis[r][c] = true;
      for (int i = 0; i < 4; i++) {
        int newI = r + DIRS[i];
        int newJ = c + DIRS[i + 1];
        if (0 <= newI && newI < arr.length && 0 <= newJ && newJ < arr[0].length &&
            !vis[newI][newJ] && arr[newI][newJ] <= arr[r][c]) {
          boolean[] pa = dfs(arr, newI, newJ, vis);
          p = p || pa[0];
          a = a || pa[1];
          if (p && a)
            return new boolean[]{true, true};
        }
      }
      return new boolean[]{p, a};
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}