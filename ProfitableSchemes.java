import java.util.Arrays;

/**
 * 879.盈利计划 <br>
 * 开题时间：2023-03-09 11:41:22
 */
public class ProfitableSchemes {
  public static void main(String[] args) {
    Solution solution = new ProfitableSchemes().new Solution();
    // System.out.println(solution.profitableSchemes(1, 1, new int[]{1, 1, 1, 1, 2, 2, 1, 2, 1, 1}, new int[]{0, 1, 0, 0, 1, 1, 1, 0, 2, 2}));
    // System.out.println(solution.profitableSchemes(1, 1, new int[]{2, 2, 2, 2, 2}, new int[]{1, 2, 1, 1, 0}));
    // System.out.println(solution.profitableSchemes(5, 3, new int[]{2, 2}, new int[]{2, 3}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int MOD = 10_0000_0007;
    
    /*
     * 前言：
     * 关于状态定义：①利润 >= j 和 ②利润 = j，两者都可以 AC、复杂度也相同，区别在于初始化和结果的处理，此处略去说明，
     * 详见 [官解评论](https://leetcode.cn/problems/profitable-schemes/solutions/819654/ying-li-ji-hua-by-leetcode-solution-3t8o/comments/978599)
     */
    
    // region 直推法
    /*
     * 01背包（求方案数（无顺序））  三维 + 等价转换
     * !!!关键：利润维度的要求是「不小于」，而不是常规的「不大于」，这需要我们对于某些状态作等价转换
     *
     * 状态定义：f[i][j][k] 为考虑前 i 种工作、利润 >= j、人数 <= k 的方案数
     * 状态转移：f[i][j][k] =
     *            不选：f[i - 1][j][k] +
     *              选：f[i - 1][Math.max(0, j - profit[i - 1])][k - group[i - 1]]
     *          !!!注意：利润为负值为「合法状态」（根据状态定义），又由于我们设计的①数组不存储负值，以及②所有工作的利润皆为非负数
     *              所以我们可以将其转化为利润 >= 0 值的状态（即 f[i - 1][0][k - group[i - 1]]）
     * 初始化　：f[0][0][k] = 1 for 0 <= k < n + 1
     * 答案　　：f[m][minProfit][n]
     */
    public int profitableSchemesA1(int n, int minProfit, int[] group, int[] profit) {
      int m = group.length;
      int[][][] f = new int[m + 1][minProfit + 1][n + 1];
      Arrays.fill(f[0][0], 1);
      
      for (int i = 1; i < m + 1; i++) {
        for (int j = 0; j < minProfit + 1; j++) {
          for (int k = 0; k < n + 1; k++) {
            f[i][j][k] = f[i - 1][j][k];
            if (k - group[i - 1] >= 0) {
              f[i][j][k] = (f[i][j][k] + f[i - 1][Math.max(0, j - profit[i - 1])][k - group[i - 1]]) % MOD;
            }
          }
        }
      }
      
      return f[m][minProfit][n];
    }
    
    // ☆☆☆☆☆ 01背包（求方案数（无顺序））  二维（去掉工作维度） + 等价转换
    public int profitableSchemesA2(int n, int minProfit, int[] group, int[] profit) {
      int m = group.length;
      int[][] f = new int[minProfit + 1][n + 1];
      Arrays.fill(f[0], 1);
      
      for (int i = 0; i < m; i++) {
        for (int j = minProfit; j >= 0; j--) {
          for (int k = n; k >= group[i]; k--) {
            f[j][k] = (f[j][k] + f[Math.max(0, j - profit[i])][k - group[i]]) % MOD;
          }
        }
      }
      
      return f[minProfit][n];
    }
    // endregion
    
    // region 作差法
    /*
     * 01背包（求方案数（无顺序））  三维 + 作差法（容斥原理）
     * [利润 >= minProfit、人数 <= n 的方案数] = ① [人数 <= n 的方案数] - ② [利润 <= minProfit - 1 、人数 <= n 的方案数]
     *
     * ① [人数 <= n 的方案数]
     * 状态定义：f[i][j] 为考虑前 i 种工作、人数 <= k 的方案数
     * 状态转移：f[i][j] =
     *            不选：f[i - 1][j] +
     *              选：f[i - 1][j - group[i - 1]] if j - group[i - 1] >= 0
     * 初始化　：f[0][j] = 1 for 0 <= j < n + 1
     * 结果　　：f[m][n]
     *
     * ！！！注意特判：minProfit = 0 时，直接返回 f[m][n]
     *
     * ② [利润 <= minProfit - 1 、人数 <= n 的方案数]
     * 状态定义：g[i][j][k] 为考虑前 i 种工作、利润 <= j、人数 <= k 的方案数
     * 状态转移：g[i][j][k] =
     *            不选：g[i - 1][j][k] +
     *              选：g[i - 1][j - profit[i - 1]][k - group[i - 1]] if (j - profit[i - 1] >= 0 && k - group[i - 1] >= 0)
     * 初始化　：g[0][j][k] = 1 for (0 <= j < minProfit && 0 <= k < n + 1)
     * 结果　　：g[m][minProfit - 1][n]
     *
     * 答案　　：f[m][n] - g[m][minProfit][n]
     */
    public int profitableSchemesB1(int n, int minProfit, int[] group, int[] profit) {
      int m = group.length;
      int[][] f = new int[m + 1][n + 1];
      Arrays.fill(f[0], 1);
      for (int i = 1; i < m + 1; i++) {
        for (int j = 0; j < n + 1; j++) {
          f[i][j] = (f[i - 1][j] +
              (j - group[i - 1] >= 0 ? f[i - 1][j - group[i - 1]] : 0)) % MOD;
        }
      }
      int ans = f[m][n];
      if (minProfit == 0) {
        return ans;
      }
      
      int[][][] g = new int[m + 1][minProfit][n + 1];
      // Arrays.fill(g[0][0], 1);
      for (int j = 0; j < minProfit; j++) {
        for (int k = 0; k < n + 1; k++) {
          g[0][j][k] = 1;
        }
      }
      
      for (int i = 1; i < m + 1; i++) {
        for (int j = 0; j < minProfit; j++) {
          for (int k = 0; k < n + 1; k++) {
            g[i][j][k] = g[i - 1][j][k];
            if (j - profit[i - 1] >= 0 && k - group[i - 1] >= 0) {
              g[i][j][k] = (g[i][j][k] + g[i - 1][j - profit[i - 1]][k - group[i - 1]]) % MOD;
            }
          }
        }
      }
      
      // for (int j = 0; j < minProfit; j++) {
      //   ans = (ans - g[m][j][n] + MOD) % MOD;
      // }
      
      return (ans - g[m][minProfit - 1][n] + MOD) % MOD;
      // return ans;
    }
    
    // ☆☆☆☆☆ 01背包（求方案数（无顺序））  二维优化（去掉工作维度） + 作差法（容斥原理）
    public int profitableSchemesB2(int n, int minProfit, int[] group, int[] profit) {
      int m = group.length;
      int[] f = new int[n + 1];
      Arrays.fill(f, 1);
      
      for (int x : group) {
        for (int j = n; j >= x; j--) {
          f[j] = (f[j] + f[j - x]) % MOD;
        }
      }
      int ans = f[n];
      if (minProfit == 0) {
        return ans;
      }
      
      int[][] g = new int[minProfit][n + 1];
      // Arrays.fill(g[0], 1);
      for (int j = 0; j < minProfit; j++) {
        for (int k = 0; k < n + 1; k++) {
          g[j][k] = 1;
        }
      }
      
      for (int i = 0; i < m; i++) {
        for (int j = minProfit - 1; j >= profit[i]; j--) {
          for (int k = n; k >= group[i]; k--) {
            g[j][k] = (g[j][k] + g[j - profit[i]][k - group[i]]) % MOD;
          }
        }
      }
      
      // for (int j = 0; j < minProfit; j++) {
      //   ans = (ans - g[j][n] + MOD) % MOD;
      // }
      
      return (ans - g[minProfit - 1][n] + MOD) % MOD;
      // return ans;
    }
    // endregion
  }
  // leetcode submit region end(Prohibit modification and deletion)
}