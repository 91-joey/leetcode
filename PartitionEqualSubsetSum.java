import java.util.Arrays;

/**
 * 416.分割等和子集 <br>
 * 开题时间：2023-03-07 16:07:11
 */
public class PartitionEqualSubsetSum {
  public static void main(String[] args) {
    Solution solution = new PartitionEqualSubsetSum().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 01背包（一维优化 + 提前返回）
     * 将问题转化为：能否找出一个子集，其元素和为 sum/2 （sum为数组所有元素和）
     *
     * 状态定义：f[i][j]表示考虑前 i 个元素，能否找出一个子集，其元素和为 j
     * 状态转移：f[i][j] = f[i-1][j] || f[i-1][j-nums[i-1]]
     * 初始化  ：f[0][0] = true
     * 答案   ：f[n][sum/2]
     */
    public boolean canPartition9(int[] nums) {
      int sum = Arrays.stream(nums).sum();
      // sum 必为偶数
      if (sum % 2 != 0) {
        return false;
      }
  
      int t = sum / 2;
      boolean[] f = new boolean[t + 1];
      f[0] = true;
      for (int x : nums) {
        for (int j = t; j >= x; j--) {
          f[j] = f[j] || f[j - x];
        }
        // 提前返回
        if (f[t]) {
          return true;
        }
      }
      return f[t];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}