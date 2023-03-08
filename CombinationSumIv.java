import java.util.Arrays;

/**
 * 377.组合总和 Ⅳ <br>
 * 开题时间：2023-03-07 17:13:19
 */
public class CombinationSumIv {
  public static void main(String[] args) {
    Solution solution = new CombinationSumIv().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // public int combinationSum4X(int[] nums, int t) {
    //   int n = nums.length;
    //   // if (n == 1) {
    //   //   return nums[0] == t ? 1 : 0;
    //   // }
    //   int[][] f = new int[n + 1][t + 1];
    //   f[0][0] = 1;
    //   for (int j = 1; j <= t; j++) {
    //     for (int i = 1; i < n + 1; i++) {
    //       f[i][j] = f[i - 1][j] + ((j - nums[i - 1]) >= 0 ? f[i][j - nums[i - 1]] : 0);
    //     }
    //   }
    //   return f[n][t];
    // }
    
    /*
     * 完全背包 + 求方案数（考虑顺序）
     * 状态定义：f[len][t] 表示总和为 t 、组合中元素个数为 len 的组合个数
     * 状态转移：f[len][t] = ∑(f[len - 1][t - nums[i]]) for 0<= i < nums.length if 0 <= t - nums[i]
     *          具体解释：组合个数 = 所有以 nums[i] 结尾（事实上任意的位置 j 都是可以的）的组合个数之和
     * 初始化  : f[0][0] = 1
     * 答案   ：∑(f[i][t]) for 0 <= i < nums.length
     *
     * 小优化：先对数组进行排序
     */
    public int combinationSum49(int[] nums, int t) {
      Arrays.sort(nums);
      int[][] f = new int[t + 1][t + 1];
      f[0][0] = 1;
      int ans = 0;
      for (int i = 1; i <= t; i++) {
        for (int j = 1; j <= t; j++) {
          for (int k = 0; k < nums.length && 0 <= j - nums[k]; k++) {
            f[i][j] += f[i - 1][j - nums[k]];
          }
        }
        ans += f[i][t];
      }
      
      return ans;
    }
  
  
    /*
     * ☆☆☆☆☆ （一维优化）完全背包 + 求方案数（考虑顺序）
     * 状态定义：f[t] 表示总和为 t 的组合个数
     * 状态转移：f[t] = ∑(f[t - nums[i]]) for 0<= i < nums.length if 0 <= t - nums[i]
     *          具体解释：组合个数 = 所有以 nums[i] 结尾（事实上任意的位置 j 都是可以的）的组合个数之和
     * 初始化  : f[0] = 1
     * 答案   ：f[t]
     *
     * 小优化：先对数组进行排序
     */
    public int combinationSum4(int[] nums, int t) {
      Arrays.sort(nums);
      int[] f = new int[t + 1];
      f[0] = 1;
      for (int j = 1; j <= t; j++) {
        for (int i = 0, n = nums.length; i < n && j - nums[i] >= 0; i++) {
          f[j] += f[j - nums[i]];
        }
      }
      return f[t];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}