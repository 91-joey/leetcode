package org.example.leetcode.problems._2_algorithm.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 994.腐烂的橘子 <br>
 * 开题时间：2023-01-27 11:50:47
 */
public class RottingOranges {
  public static void main(String[] args) {
    Solution solution = new RottingOranges().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    
    // 多源BFS（较繁琐：访问标记数组 + 记录最后一个新鲜橘子变腐的时间）
    public int orangesRotting9(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      Queue<int[]> q = new LinkedList<>();
      boolean[][] vis = new boolean[m][n];
      
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == 2) {
            q.offer(new int[]{i, j});
            vis[i][j] = true;
          } else if (grid[i][j] == 0) {
            vis[i][j] = true;
          }
        }
      }
      
      for (int i = 0; i < m; i++) {
        Arrays.fill(grid[i], 0);
      }
      
      int minutes = 0;
      while (!q.isEmpty()) {
        int[] poll = q.poll();
        int r = poll[0];
        int c = poll[1];
        for (int j = 0; j < 4; j++) {
          int nr = r + DIRS[j];
          int nc = c + DIRS[j + 1];
          if (0 <= nr && nr < m && 0 <= nc && nc < n
              && !vis[nr][nc]) {
            grid[nr][nc] = grid[r][c] + 1;
            minutes = grid[nr][nc];
            q.offer(new int[]{nr, nc});
            vis[nr][nc] = true;
          }
        }
      }
      
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (!vis[i][j]) {
            return -1;
          }
        }
      }
      
      return minutes;
    }
    
    //☆☆☆☆☆ 多源BFS（精简版：覆盖原数组元素值，代替访问标记数组 + 维护新鲜橘子数）
    public int orangesRotting(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      Queue<int[]> q = new LinkedList<>();
      
      int cntFresh = 0;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == 2) {
            q.offer(new int[]{i, j});
          } else if (grid[i][j] == 1) {
            cntFresh++;
          }
        }
      }
      
      int minutes = 0;
      while (cntFresh > 0 && !q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          int[] poll = q.poll();
          int r = poll[0];
          int c = poll[1];
          for (int j = 0; j < 4; j++) {
            int nr = r + DIRS[j];
            int nc = c + DIRS[j + 1];
            if (0 <= nr && nr < m && 0 <= nc && nc < n
                && grid[nr][nc] == 1) {
              grid[nr][nc] = 2;
              q.offer(new int[]{nr, nc});
              cntFresh--;
            }
          }
        }
        minutes++;
      }
      
      return cntFresh == 0 ? minutes : -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}