package org.example.leetcode.problems._2_algorithm.dfs;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;

/**
 * 1034.边界着色 <br>
 * 开题时间：2023-02-15 17:36:21
 */
public class ColoringABorder {
  public static void main(String[] args) {
    Solution solution = new ColoringABorder().new Solution();
    System.out.println(Arrays.deepToString(solution.colorBorder(Tools.to2DIntArray("[[1,1,1],[1,1,1],[1,1,1]]"), 1, 1, 2)));
    // System.out.println(Arrays.deepToString(solution.colorBorder(Tools.to2DIntArray("[[1,1],[1,2]]"), 0, 0, 3)));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    
    // dfs + 遍历判断是否为连通分量的边界
    public int[][] colorBorder9(int[][] grid, int row, int col, int color) {
      int n = grid.length;
      int m = grid[0].length;
      boolean[][] vis = new boolean[n][m];
      
      dfs9(grid, row, col, grid[row][col], vis);
      
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
          if (vis[r][c]) {
            if (r == 0 || c == 0 || r == n - 1 || c == m - 1) {
              grid[r][c] = color;
              continue;
            }
            for (int k = 0; k < 4; k++) {
              int nr = r + DIRS[k];
              int nc = c + DIRS[k + 1];
              if (!vis[nr][nc]) {
                grid[r][c] = color;
                break;
              }
            }
          }
        }
      }
      
      return grid;
    }
    
    private void dfs9(int[][] arr, int r, int c, int color, boolean[][] vis) {
      vis[r][c] = true;
      for (int i = 0; i < 4; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < arr.length && 0 <= nc && nc < arr[0].length &&
            !vis[nr][nc] && arr[nr][nc] == color) {
          dfs9(arr, nr, nc, color, vis);
        }
      }
    }
  
  
    // ☆☆☆☆☆ dfs的同时判断是否为连通分量的边界 + 遍历
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
      int n = grid.length;
      int m = grid[0].length;
      boolean[][] vis = new boolean[n][m];
      
      dfs(grid, row, col, grid[row][col], vis, color);
      
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
          if (grid[r][c] == Integer.MAX_VALUE) {
            grid[r][c] = color;
          }
        }
      }
      
      return grid;
    }
    
    private void dfs(int[][] arr, int r, int c, int cur, boolean[][] vis, int color) {
      vis[r][c] = true;
      int cntAdj = 0;
      for (int i = 0; i < 4; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < arr.length && 0 <= nc && nc < arr[0].length) {
          if (arr[nr][nc] == Integer.MAX_VALUE || arr[nr][nc] == cur) {
            cntAdj++;
            if (!vis[nr][nc]) {
              dfs(arr, nr, nc, cur, vis, color);
            }
          }
        }
      }
      if (cntAdj < 4) {
        arr[r][c] = Integer.MAX_VALUE;
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}