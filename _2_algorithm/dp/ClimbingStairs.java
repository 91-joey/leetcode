package _2_algorithm.dp;

/**
 * 70.爬楼梯 <br>
 * 开题时间：2023-01-30 09:45:13
 */
public class ClimbingStairs {
  public static void main(String[] args) {
    Solution solution = new ClimbingStairs().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int climbStairs9(int n) {
      int[] f = new int[n + 1];
      f[0] = f[1] = 1;
      for (int i = 2; i <= n; i++) {
        f[i] = f[i - 2] + f[i - 1];
      }
      return f[n];
    }
    
    public int climbStairs(int n) {
      int pre = 1;
      int cur = 1;
      for (int i = 2; i <= n; i++) {
        int tmp = cur;
        cur += pre;
        pre = tmp;
      }
      return cur;
    }
    
    // todo 矩阵快速幂
  }
  // leetcode submit region end(Prohibit modification and deletion)
}