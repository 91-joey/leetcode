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
    public int combinationSum4X(int[] nums, int t) {
      int n = nums.length;
      // if (n == 1) {
      //   return nums[0] == t ? 1 : 0;
      // }
      int[][] f = new int[n + 1][t + 1];
      f[0][0] = 1;
      for (int j = 1; j <= t; j++) {
        for (int i = 1; i < n + 1; i++) {
          f[i][j] = f[i - 1][j] + ((j - nums[i - 1]) >= 0 ? f[i][j - nums[i - 1]] : 0);
        }
      }
      return f[n][t];
    }
    
    public int combinationSum4(int[] nums, int t) {
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
    
    public int combinationSum49(int[] nums, int t) {
      int[] f = new int[t + 1];
      f[0] = 1;
      for (int j = 1; j <= t; j++) {
        for (int x : nums) {
          if (j - x >= 0) {
            f[j] += f[j - x];
          }
        }
      }
      return f[t];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}