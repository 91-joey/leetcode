package org.example.leetcode.problems._1_dataStructure.arrayAndString;

/**
 * 2319.判断矩阵是否是一个 X 矩阵 <br>
 * 开题时间：2023-01-31 09:52:33
 */
public class CheckIfMatrixIsXMatrix {
  public static void main(String[] args) {
    Solution solution = new CheckIfMatrixIsXMatrix().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean checkXMatrix9(int[][] grid) {
      int n = grid.length;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (i == j || i + j == n - 1) {
            if (grid[i][j] == 0) {
              return false;
            }
          } else {
            if (grid[i][j] != 0) {
              return false;
            }
          }
        }
      }
      return true;
    }
    
    // ☆☆☆☆☆ 优化（判断俩布尔值是否相等）
    public boolean checkXMatrix(int[][] grid) {
      int n = grid.length;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if ((i == j || i + j == n - 1) == (grid[i][j] == 0)) {
            return false;
          }
        }
      }
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}