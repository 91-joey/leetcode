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
  /*
   * 共有 2 种解法：「子集视角：状压 + 记搜」、「元素视角：回溯 + 剪枝」
   * 总体来看：
   *  「元素视角：回溯 + 剪枝」的时间复杂度较优，但剪枝较不容易想到
   *  「子集视角：状压 + 记搜」的时间复杂度较差，但容易想到
   */
  class Solution {
    
    int t;
    int[] subsets;
    private int[] nums;
    private int k;
    Boolean[] memo;
    
    // 状压 + 记搜（子集视角：每个子集去选择元素（每个元素都有选与不选 2 种情况））  2^{n*k}
    public boolean canPartitionKSubsets9(int[] nums, int k) {
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
      
      return dfs(0, k, t, 0);
    }
    
    /**
     * 当前的元素的使用状态下，能否组分成 k 个非空子集
     *
     * @param state 元素的使用状态（二进制下，从右往左第 i 位为 1，表示数组第 i 个元素已使用）
     * @param k     剩余待填充子集数
     * @param t     当前子集剩余待填充元素和
     * @param start 数组的枚举起始索引
     */
    private boolean dfs(int state, int k, int t, int start) {
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
        return memo[state] = dfs(state, k - 1, this.t, Integer.numberOfTrailingZeros(~state));
      }
      
      for (int i = start; i < nums.length; i++) {
        if ((state & (1 << i)) != 0 || t - nums[i] < 0) {
          continue;
        }
        if (dfs(state | (1 << i), k, t - nums[i], i + 1)) {
          return memo[state] = true;
        }
        // 决策树的同一节点使用相同元素，效果相同
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
          i++;
        }
      }
      
      return memo[state] = false;
    }
    
    // ☆☆☆☆☆ 回溯 + 剪枝（元素视角：每个元素去选择子集（每个元素都有 k 种情况））  k^n
    public boolean canPartitionKSubsets(int[] nums, int k) {
      this.nums = nums;
      this.k = k;
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
      
      // 每个子集的当前元素和
      subsets = new int[k];
      
      /*
       * 逆序遍历，从较大的元素开始遍历，可以增加剪枝的命中率，从而降低回溯的概率
       * 比如：subsets[j]=1, t=8, nums[0:i]={1,2,8}
       *  顺序遍历：1->2->8 END（1+1+2+8>t）
       *  逆序遍历：8->END（1+8>t）
       * 可以看到逆序遍历走一步就提前终止了这条路径
       */
      return backtrack(nums.length - 1);
    }
    
    private boolean backtrack(int i) {
      if (i < 0) {
        return true;
      }
      
      for (int j = 0; j < k; j++) {
        // 元素总和不能大于 t
        if (subsets[j] + nums[i] > t) {
          continue;
        }
        subsets[j] += nums[i];
        if (backtrack(i - 1)) {
          return true;
        }
        subsets[j] -= nums[i];
        // 将元素放入当前元素和相同的子集，效果相同
        while (j + 1 < k && subsets[j] == subsets[j + 1]) {
          j++;
        }
      }
      
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}