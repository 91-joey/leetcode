
/**
 * 518.零钱兑换 II <br>
 * 开题时间：2023-03-08 17:56:36
 */
public class CoinChangeIi {
  public static void main(String[] args) {
    Solution solution = new CoinChangeIi().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * ☆☆☆☆☆ 完全背包（求方案数（无顺序））
     */
    public int change(int amount, int[] coins) {
      int[] f = new int[amount + 1];
      f[0] = 1;
      for (int x : coins) {
        for (int j = x; j < f.length; j++) {
          f[j] += f[j - x];
        }
      }
      return f[amount];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}