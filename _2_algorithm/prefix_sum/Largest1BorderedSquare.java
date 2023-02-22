package _2_algorithm.prefix_sum;

import _3_common.tool.Tools;

/**
 * 1139.最大的以 1 为边界的正方形 <br>
 * 开题时间：2023-02-17 09:12:54
 */
public class Largest1BorderedSquare {
  public static void main(String[] args) {
    Solution solution = new Largest1BorderedSquare().new Solution();
    System.out.println(solution.largest1BorderedSquare(Tools.to2DIntArray("[[1,1,1],[1,0,1],[1,1,1]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 暴力
    public int largest1BorderedSquare9(int[][] grid) {
      int n = grid.length;
      int m = grid[0].length;
      
      for (int len = Math.min(n, m); len > 0; len--) {
        for (int i = 0; i < n - len + 1; i++) {
          out:
          for (int j = 0; j < m - len + 1; j++) {
            for (int r = i; r < i + len; r++) {
              if (grid[r][j] == 0 || grid[r][j + len - 1] == 0) {
                continue out;
              }
            }
            for (int c = j; c < j + len; c++) {
              if (grid[i][c] == 0 || grid[i + len - 1][c] == 0) {
                continue out;
              }
            }
            return len * len;
          }
        }
      }
      
      return 0;
    }
    
    // 前缀和 + 枚举边长和左上角坐标
    public int largest1BorderedSquare8(int[][] grid) {
      int n = grid.length;
      int m = grid[0].length;
      
      int[][] prefixC = new int[n][m + 1];
      int[][] prefixR = new int[n + 1][m];
      for (int i = 0; i < n; i++) {
        for (int j = 1; j < m + 1; j++) {
          prefixC[i][j] = prefixC[i][j - 1] + grid[i][j - 1];
        }
      }
      
      for (int j = 0; j < m; j++) {
        for (int i = 1; i < n + 1; i++) {
          prefixR[i][j] = prefixR[i - 1][j] + grid[i - 1][j];
        }
      }
      
      for (int len = Math.min(n, m); len > 0; len--) {
        for (int i = 0; i < n - len + 1; i++) {
          for (int j = 0; j < m - len + 1; j++) {
            if (prefixR[i + len][j] - prefixR[i][j] == len &&
                prefixC[i][j + len] - prefixC[i][j] == len &&
                prefixC[i + len - 1][j + len] - prefixC[i + len - 1][j] == len &&
                prefixR[i + len][j + len - 1] - prefixR[i][j + len - 1] == len) {
              return len * len;
            }
          }
        }
      }
      
      return 0;
    }
    
    /*
     * ☆☆☆☆☆ （优化）前缀和 + 枚举边长和左上角坐标
     * 主要就是两点要注意的：
     *  - `+ 1` 这种要细心
     *  - 行列前缀和可以合并在一起写。
     * 收获：01矩阵要想到「前缀和」，它适用于多次查询区间值的情况，属于是空间换时间
     */
    public int largest1BorderedSquare(int[][] grid) {
      int n = grid.length;
      int m = grid[0].length;
      int[][] sc = new int[n][m + 1];
      int[][] sr = new int[m][n + 1];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          sc[i][j + 1] = sc[i][j] + grid[i][j];
          sr[j][i + 1] = sr[j][i] + grid[i][j];
        }
      }
  
      for (int d = Math.min(n, m); d > 0; d--) {
        for (int r1 = 0, r2 = r1 + d - 1; r2 < n; r1++, r2++) {
          for (int c1 = 0, c2 = c1 + d - 1; c2 < m; c1++, c2++) {
            if (sc[r1][c2 + 1] - sc[r1][c1] == d &&
                sc[r2][c2 + 1] - sc[r2][c1] == d &&
                sr[c1][r2 + 1] - sr[c1][r1] == d &&
                sr[c2][r2 + 1] - sr[c2][r1] == d) {
              return d * d;
            }
          }
        }
      }
  
      return 0;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}