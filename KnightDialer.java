import java.util.Arrays;

/**
 * 935.骑士拨号器 <br>
 * 开题时间：2023-03-16 16:29:31
 */
public class KnightDialer {
  
  
  public static void main(String[] args) {
    Solution solution = new KnightDialer().new Solution();
    System.out.println(solution);
    // 04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int MOD = 1000000007;
    public static final int[][] JUMP_FROM = {
        {4, 6},
        {6, 8},
        {7, 9},
        {4, 8},
        {0, 3, 9},
        {},
        {0, 1, 7},
        {2, 6},
        {1, 3},
        {2, 4},
    };
    
    /*
     * 线性dp
     * 状态定义：f[i][j]表示号码长度为 i+1 、以数字 j 结尾的不同号码的数量
     * 状态转移：f[i][j] = ∑ f[i - 1][k] % MOD 要求从数字 k 能跳跃到 j
     *    具体跳跃规则见 JUMP_FROM，！！注意要细心
     * 初始化　：f[0][j] = 1 for all 0 <= j < 10
     * 答案　　： ∑ f[n - 1][j] % MOD if 0 <= j < 10
     *    ！！注意求和也要取模
     */
    public int knightDialer9(int n) {
      int[][] f = new int[n][10];
      Arrays.fill(f[0], 1);
      
      for (int i = 1; i < n; i++) {
        for (int j = 0; j < 10; j++) {
          for (int k : JUMP_FROM[j]) {
            f[i][j] = (f[i][j] + f[i - 1][k]) % MOD;
          }
        }
      }
      
      int ans = 0;
      for (int j = 0; j < 10; j++) {
        ans = (ans + f[n - 1][j]) % MOD;
      }
      return ans;
    }
    
    /*
     * ☆☆☆☆☆ 四态dp
     * 只有长度为 1 时，数字 5 是可跳跃的
     * 其余数字可以分为 4 个组群，以每个组群中的数字结尾的号码数都是相同的，具体的：
     *    组群 a：{1,3,7,9}
     *    组群 b：{2,8}
     *    组群 c：{4,6}
     *    组群 d：{0}
     *
     * 状态定义：f[i] 表示组群 i 中的每个数字结尾的号码数（分别用 a、b、c、d 表示）
     * 状态转移：
     *    a = b + c
     *    b = 2 * a
     *    c = 2 * a + d
     *    d = 2 * c
     * 初始化　：f[i] = 1 for all 0 <= i < 4
     * 答案　　：4 * a + 2 * (b + c) + d
     */
    public int knightDialer(int n) {
      if (n == 1) {
        return 10;
      }
      
      long[] f = {1, 1, 1, 1};
      for (int i = 1; i < n; i++) {
        long a = f[0];
        long b = f[1];
        long c = f[2];
        long d = f[3];
        f[0] = (b + c) % MOD;
        f[1] = (2 * a) % MOD;
        f[2] = (f[1] + d) % MOD;
        f[3] = (2 * c) % MOD;
      }
      
      return (int) ((4 * f[0] + 2 * (f[1] + f[2]) + f[3]) % MOD);
    }
    
    // todo 矩阵快速幂
  }
  // leetcode submit region end(Prohibit modification and deletion)
}