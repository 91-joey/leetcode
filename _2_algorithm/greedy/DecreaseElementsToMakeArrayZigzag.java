package _2_algorithm.greedy;

import java.util.Arrays;

/**
 * 1144.递减元素使数组呈锯齿状 <br>
 * 开题时间：2023-02-27 09:42:54
 */
public class DecreaseElementsToMakeArrayZigzag {
  public static void main(String[] args) {
    Solution solution = new DecreaseElementsToMakeArrayZigzag().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int movesToMakeZigzagX(int[] nums) {
      int n = nums.length;
      int odd = 0;
      for (int i = 0; i < n; i += 2) {
        odd += Math.max(0, nums[i] - Math.min((i == 0 ? Integer.MAX_VALUE : nums[i - 1]), (i == n - 1 ? Integer.MAX_VALUE : nums[i + 1])) + 1);
      }
      
      int even = 0;
      for (int i = 0; i < n; i += 2) {
        even += Math.max(0, -nums[i] + Math.max((i == 0 ? Integer.MIN_VALUE : nums[i - 1]), (i == n - 1 ? Integer.MIN_VALUE : nums[i + 1])) + 1);
      }
      
      return Math.min(odd, even);
    }
    
    // 贪心 + 分类讨论（山顶元素不需要减少，山脚元素根据两侧元素的最小值减少）
    public int movesToMakeZigzag9(int[] nums) {
      int n = nums.length;
      
      int odd = 0;
      for (int i = 0; i < n; i += 2) {
        odd += Math.max(0, nums[i] - Math.min((i == 0 ? Integer.MAX_VALUE : nums[i - 1]), (i == n - 1 ? Integer.MAX_VALUE : nums[i + 1])) + 1);
      }
      
      int even = 0;
      for (int i = 1; i < n; i += 2) {
        even += Math.max(0, nums[i] - Math.min(nums[i - 1], (i == n - 1 ? Integer.MAX_VALUE : nums[i + 1])) + 1);
      }
      
      return Math.min(odd, even);
    }
  
    // （简化）贪心 + 分类讨论（山顶元素不需要减少，山脚元素根据两侧元素的最小值减少）
    public int movesToMakeZigzag(int[] nums) {
      int n = nums.length;
      int[] s = new int[2];
      for (int i = 0; i < n; i++) {
        int l = i <= 0 ? Integer.MAX_VALUE : nums[i - 1];
        int r = i >= n - 1 ? Integer.MAX_VALUE : nums[i + 1];
        s[i & 1] += Math.max(0, nums[i] - Math.min(l, r) + 1);
      }
      
      return Arrays.stream(s).min().getAsInt();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}