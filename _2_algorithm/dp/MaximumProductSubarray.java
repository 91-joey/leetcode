//<p>给你一个整数数组 <code>nums</code>&nbsp;，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。</p>
//
//<p>测试用例的答案是一个&nbsp;<strong>32-位</strong> 整数。</p>
//
//<p><strong>子数组</strong> 是数组的连续子序列。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [2,3,-2,4]
//<strong>输出:</strong> <span><code>6</code></span>
//<strong>解释:</strong>&nbsp;子数组 [2,3] 有最大乘积 6。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [-2,0,-1]
//<strong>输出:</strong> 0
//<strong>解释:</strong>&nbsp;结果不能为 2, 因为 [-2,-1] 不是子数组。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>-10 &lt;= nums[i] &lt;= 10</code></li> 
// <li><code>nums</code> 的任何前缀或后缀的乘积都 <strong>保证</strong>&nbsp;是一个 <strong>32-位</strong> 整数</li> 
//</ul>
//
//<div><li>👍 1852</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 152.乘积最大子数组
// 开题时间：2022-11-22 18:14:56
public class MaximumProductSubarray {
  public static void main(String[] args) {
    Solution solution = new MaximumProductSubarray().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maxProduct9(int[] nums) {
      int max = nums[0];
      for (int i = 1, maxPreMin = max, maxPreMax = max; i < nums.length; i++) {
        int tmp = maxPreMin;
        maxPreMin = Math.min(Math.min(nums[i], maxPreMin * nums[i]), maxPreMax * nums[i]);
        maxPreMax = Math.max(Math.max(nums[i], maxPreMax * nums[i]), tmp * nums[i]);
        max = Math.max(max, maxPreMax);
      }
      return max;
    }
    
    // DP 负数时：交换最值
    public int maxProduct(int[] nums) {
      int max = Integer.MIN_VALUE;
      for (int i = 0, iMin = 1, iMax = 1; i < nums.length; i++) {
        if (nums[i] < 0) {
          int tmp = iMin;
          iMin = iMax;
          iMax = tmp;
        }
        iMin = Math.min(nums[i], iMin * nums[i]);
        iMax = Math.max(nums[i], iMax * nums[i]);
        max = Math.max(max, iMax);
      }
      return max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}