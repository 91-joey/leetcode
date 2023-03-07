package _2_algorithm.dp;

import _3_common.tool.Tools;

/**
 * 剑指 Offer 47.礼物的最大价值 <br>
 * 开题时间：2023-03-08 09:13:43
 */
public class LiWuDeZuiDaJieZhiLcof {
  public static void main(String[] args) {
    Solution solution = new LiWuDeZuiDaJieZhiLcof().new Solution();
    System.out.println(solution.maxValue(Tools.to2DIntArray("[[1,3,1],[1,5,1],[4,2,1]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // dp
    public int maxValue9(int[][] grid) {
      int n = grid.length;
      int m = grid[0].length;
      int[][] f = new int[n + 1][m + 1];
      for (int i = 1; i < n + 1; i++) {
        for (int j = 1; j < m + 1; j++) {
          f[i][j] = grid[i - 1][j - 1] + Math.max(f[i - 1][j], f[i][j - 1]);
        }
      }
      return f[n][m];
    }
    
    // dp（一维优化）
    public int maxValue(int[][] grid) {
      int n = grid.length;
      int m = grid[0].length;
      int[] f = new int[m + 1];
      for (int[] row : grid) {
        for (int j = 1; j < m + 1; j++) {
          f[j] = row[j - 1] + Math.max(f[j], f[j - 1]);
        }
      }
      return f[m];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}