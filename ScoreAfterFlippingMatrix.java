package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

/**
 * 861.翻转矩阵后的得分 <br>
 * 开题时间：2023-01-27 15:35:43
 */
public class ScoreAfterFlippingMatrix {
  public static void main(String[] args) {
    Solution solution = new ScoreAfterFlippingMatrix().new Solution();
    System.out.println(solution.matrixScore(Tools.to2DIntArray("[[0,0,1,1],[1,0,1,0],[1,1,0,0]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int matrixScore9(int[][] grid) {
      int ans = 0;
      int m = grid.length;
      int n = grid[0].length;
      
      for (int i = 0; i < m; i++) {
        if (grid[i][0] == 0) {
          for (int j = 0; j < n; j++) {
            grid[i][j] ^= 1;
          }
        }
      }
      
      for (int i = 0; i < n; i++) {
        int cnt0 = 0;
        for (int j = 0; j < m; j++) {
          if (grid[j][i] == 0) {
            cnt0++;
          }
        }
        ans += Math.max(cnt0, m - cnt0) * (1 << (n - 1 - i));
      }
      
      return ans;
    }
  
    /*
     * ☆☆☆☆☆ 贪心
     *
     * 翻转 2 次等价于没有翻转，因此可以假定 行/列 只有 2 种情况：翻转/不翻转
     * 另外，对于每个元素，行列的翻转顺序是无关紧要的，因此对于整个矩阵也是一样。
     *
     * 我们先进行 行翻转，贪心得让每一行的第一个元素为 1
     * 再从第二列开始，若当前列的 1 的个数小于 0 的个数，进行 列翻转
     *
     * 实现优化：
     * 不用每行解析为二进制数再求和，直接计算每一列对答案的贡献（每一列 1 的个数 * 当前列所在二进制位的权值）
     */
    public int matrixScore(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      int ans = m * (1 << (n - 1));
      
      for (int j = 1; j < n; j++) {
        int cnt1 = 0;
        for (int i = 0; i < m; i++) {
          cnt1 += grid[i][j] ^ 1 ^ grid[i][0];
        }
        ans += Math.max(cnt1, m - cnt1) * (1 << (n - 1 - j));
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}