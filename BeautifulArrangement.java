
/**
 * 526.优美的排列 <br>
 * 开题时间：2023-03-16 10:10:43
 */
public class BeautifulArrangement {
  public static void main(String[] args) {
    Solution solution = new BeautifulArrangement().new Solution();
    System.out.println(solution.countArrangement(2));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int ans = 0;
    private int n;
    int[] memo;
    
    // 状压 + 记搜
    public int countArrangement9(int n) {
      
      this.n = n;
      memo = new int[1 << n];
      
      return dfs(0, 1);
    }
  
    /**
     * 求整数池的使用状态为 s 时的方案数
     * @param s 当前整数池的使用状态
     * @param i 排列的索引
     */
    private int dfs(int s, int i) {
      if (i == n + 1) {
        return memo[s] = 1;
      }
      
      int ans = 0;
      for (int x = 1; x <= n; x++) {
        if ((s & (1 << (x - 1))) != 0) {
          continue;
        }
        if (x % i != 0 && i % x != 0) {
          continue;
        }
        ans += dfs(s | (1 << (x - 1)), i + 1);
      }
      
      return memo[s] = ans;
    }
    
    /*
     * 状压动规
     * 状态定义：f[i][s]表示排列的前 i 个数、整数池的使用状态为 s 的方案数
     * 状态转移：f[i][s] = ∑ f[i - 1][s ^ (1 << (x - 1))];
     *          条件：
     *          - 整数池的使用个数 = i
     *          - 枚举第 i 个数 x
     *            - x ∈ s
     *            - i % x == 0 || x % i == 0
     * 初始化　：f[0][0] = 1
     * 答案　　：f[n][(1 << n) - 1]
     */
    public int countArrangement8(int n) {
      int mask = 1 << n;
      int[][] f = new int[n + 1][mask];
      f[0][0] = 1;
      
      for (int i = 1; i <= n; i++) {
        for (int s = 0; s < mask; s++) {
          if (Integer.bitCount(s) != i) {
            continue;
          }
          for (int x = 1; x <= n; x++) {
            if ((s & (1 << (x - 1))) == 0) {
              continue;
            }
            if (x % i != 0 && i % x != 0) {
              continue;
            }
            f[i][s] += f[i - 1][s ^ (1 << (x - 1))];
          }
        }
        
      }
      
      return f[n][(mask) - 1];
    }
  
    /*
     * ☆☆☆☆☆ 状压动规（根据「整数池的使用个数 = 当前已排列的个数 i」，进行一维优化）
     * 状态定义：f[s]表示整数池的使用状态为 s（整数池的使用个数 = 当前已排列的个数 i）的方案数
     * 状态转移：f[s] = ∑ f[s ^ (1 << (x - 1))];
     *          条件：
     *          - 枚举第 i 个数 x
     *            - x ∈ s
     *            - i % x == 0 || x % i == 0
     * 初始化　：f[0] = 1
     * 答案　　：f[(1 << n) - 1]
     */
    public int countArrangement(int n) {
      int[] f = new int[1 << n];
      f[0] = 1;
      
      for (int s = 1; s < f.length; s++) {
        int i = Integer.bitCount(s);
        for (int x = 1; x <= n; x++) {
          if ((s & (1 << (x - 1))) == 0) {
            continue;
          }
          if (x % i != 0 && i % x != 0) {
            continue;
          }
          f[s] += f[s ^ (1 << (x - 1))];
        }
      }
      
      return f[f.length - 1];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}