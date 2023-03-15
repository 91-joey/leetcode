import java.util.Arrays;

/**
 * 698.划分为k个相等的子集 <br>
 * 开题时间：2023-03-15 10:27:46
 */
public class PartitionToKEqualSumSubsets {
  public static void main(String[] args) {
    Solution solution = new PartitionToKEqualSumSubsets().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    
    int t;
    private int[] nums;
    Boolean[] memo;
    
    // 状压 + 记搜（子集视角：每个子集去选择元素（每个元素都有选与不选 2 种情况））  2^{n*k}
    public boolean canPartitionKSubsets(int[] nums, int k) {
      this.nums = nums;
      // 预处理：k 个非空子集的总和相等，设每个子集的总和为 t，则有：t=sum/k，t为整数
      int sum = Arrays.stream(nums).sum();
      if (sum % k != 0) {
        return false;
      }
      
      t = sum / k;
      Arrays.sort(nums); // 排序，方便对相同元素进行剪枝
      if (nums[nums.length - 1] > t) {
        return false;
      }
      
      memo = new Boolean[1 << nums.length];
      
      return canPartitionKSubsets(0, k, t, 0);
    }
  
    /**
     * 当前的元素的使用状态下，能否组分成 k 个非空子集
     * @param state 元素的使用状态（二进制下，从右往左第 i 位为 1，表示数组第 i 个元素已使用）
     * @param k 剩余待填充子集数
     * @param t 当前子集剩余待填充元素和
     * @param start 数组的枚举起始索引
     */
    private boolean canPartitionKSubsets(int state, int k, int t, int start) {
      // 记忆化
      if (memo[state] != null) {
        return memo[state];
      }
      
      // 决策树终层
      if (k == 0) {
        return memo[state] = true;
      }
      // 当前子集填充完毕，开始填充下一个子集。重置目标元素和，从第一个未使用的元素索引开始枚举
      if (t == 0) {
        return memo[state] = canPartitionKSubsets(state, k - 1, this.t, Integer.numberOfTrailingZeros(~state));
      }
      
      for (int i = start; i < nums.length; i++) {
        if ((state & (1 << i)) != 0 || t - nums[i] < 0) {
          continue;
        }
        if (canPartitionKSubsets(state | (1 << i), k, t - nums[i], i + 1)) {
          return memo[state] = true;
        }
        // 决策树的同一节点使用相同元素，效果相同
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
          i++;
        }
      }
      
      return memo[state] = false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}