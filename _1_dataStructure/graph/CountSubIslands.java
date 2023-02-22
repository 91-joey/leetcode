//<p>给你两个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>grid1</code> 和&nbsp;<code>grid2</code>&nbsp;，它们只包含&nbsp;<code>0</code>&nbsp;（表示水域）和 <code>1</code>&nbsp;（表示陆地）。一个 <strong>岛屿</strong>&nbsp;是由 <strong>四个方向</strong>&nbsp;（水平或者竖直）上相邻的&nbsp;<code>1</code>&nbsp;组成的区域。任何矩阵以外的区域都视为水域。</p>
//
//<p>如果 <code>grid2</code>&nbsp;的一个岛屿，被 <code>grid1</code>&nbsp;的一个岛屿&nbsp;<strong>完全</strong> 包含，也就是说 <code>grid2</code>&nbsp;中该岛屿的每一个格子都被 <code>grid1</code>&nbsp;中同一个岛屿完全包含，那么我们称 <code>grid2</code>&nbsp;中的这个岛屿为 <strong>子岛屿</strong>&nbsp;。</p>
//
//<p>请你返回 <code>grid2</code>&nbsp;中 <strong>子岛屿</strong>&nbsp;的 <strong>数目</strong>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/06/10/test1.png" style="width: 493px; height: 205px;"> <pre><b>输入：</b>grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
//<b>输出：</b>3
//<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
// grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
//</pre> </img>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/06/03/testcasex2.png" style="width: 491px; height: 201px;"> <pre><b>输入：</b>grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
//<b>输出：</b>2 
//<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
// grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
//</pre> </img>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == grid1.length == grid2.length</code></li> 
// <li><code>n == grid1[i].length == grid2[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 500</code></li> 
// <li><code>grid1[i][j]</code> 和&nbsp;<code>grid2[i][j]</code>&nbsp;都要么是&nbsp;<code>0</code>&nbsp;要么是&nbsp;<code>1</code>&nbsp;。</li> 
//</ul>
//
//<div><li>👍 88</li><li>👎 0</li></div>
package _1_dataStructure.graph;

import _3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 1905.统计子岛屿
// 开题时间：2023-01-03 16:48:23
public class CountSubIslands {
  public static void main(String[] args) {
    Solution solution = new CountSubIslands().new Solution();
    //        System.out.println(solution.countSubIslands(
    //                Tools.to2DIntArray("[[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]]"),
    //                Tools.to2DIntArray("[[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]")
    //        ));
    System.out.println(solution.countSubIslands(
        Tools.to2DIntArray("[[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]]"),
        Tools.to2DIntArray("[[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]")
    ));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    
    /*
     * TLE  (m*n)^2 * log(m*n)
     * - 将地图1的所有岛屿存入集合中，再将集合按照岛屿的面积排序
     * - 遍历地图2，对于每个岛屿，都进行子岛屿的校验。
     *      具体的，利用二分查找找到第一个大于等于此岛屿面积的岛屿索引，从此索引开始往后遍历，如果此岛屿的所有单元格都在当前遍历的岛屿内（利用哈希表），则此岛屿为子岛屿。
     */
    public int countSubIslandsX(int[][] grid1, int[][] grid2) {
      int m = grid1.length;
      int n = grid1[0].length;
      ArrayList<Set<Integer>> list = new ArrayList<>();
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          if (grid1[i][j] == 1) {
            HashSet<Integer> cells = new HashSet<>();
            dfs(grid1, i, j, cells);
            list.add(cells);
          }
      list.sort(Comparator.comparingInt(Set::size));
      
      int ans = 0;
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          if (grid2[i][j] == 1) {
            HashSet<Integer> cells = new HashSet<>();
            dfs(grid2, i, j, cells);
            int idx = binarySearch(list, cells.size());
            if (idx == list.size())
              continue;
            
            loop:
            for (int k = idx; k < list.size(); k++) {
              for (Integer cell : cells)
                if (!list.get(k).contains(cell))
                  continue loop;
              ans++;
              break;
            }
          }
      
      return ans;
    }
    
    
    /*
     * ☆☆☆☆☆ DFS
     * “同时”遍历两张地图，若地图1的当前单元格为水域、则此岛屿不是子岛屿
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
      int ans = 0;
      for (int i = 0; i < grid1.length; i++)
        for (int j = 0; j < grid1[0].length; j++)
          if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j))
            ans++;
      return ans;
    }
    
    private boolean dfs(int[][] grid1, int[][] grid2, int i, int j) {
      boolean isSubIsland = grid1[i][j] == 1;
      grid2[i][j] = 0;
      for (int k = 0; k < 4; k++) {
        int newI = i + DIRS[k];
        int newJ = j + DIRS[k + 1];
        if (0 <= newI && newI < grid2.length && 0 <= newJ && newJ < grid2[0].length &&
            grid2[newI][newJ] == 1)
          isSubIsland &= dfs(grid1, grid2, newI, newJ);
      }
      return isSubIsland;
    }
    
    private void dfs(int[][] grid, int i, int j, Set<Integer> set) {
      grid[i][j] = 0;
      set.add(i * grid[0].length + j);
      for (int k = 0; k < 4; k++) {
        int newI = i + DIRS[k];
        int newJ = j + DIRS[k + 1];
        if (0 <= newI && newI < grid.length && 0 <= newJ && newJ < grid[0].length &&
            grid[newI][newJ] == 1)
          dfs(grid, newI, newJ, set);
      }
    }
    
    public static int binarySearch(List<Set<Integer>> list, int target) {
      int l = 0, r = list.size();
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if (target <= list.get(mid).size())
          r = mid;
        else
          l = mid + 1;
      }
      return r;
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}