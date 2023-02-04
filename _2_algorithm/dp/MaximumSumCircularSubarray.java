//<p>给定一个长度为 <code>n</code> 的<strong>环形整数数组</strong>&nbsp;<code>nums</code>&nbsp;，返回<em>&nbsp;<code>nums</code>&nbsp;的非空 <strong>子数组</strong> 的最大可能和&nbsp;</em>。</p>
//
//<p><strong>环形数组</strong><em>&nbsp;</em>意味着数组的末端将会与开头相连呈环状。形式上， <code>nums[i]</code> 的下一个元素是 <code>nums[(i + 1) % n]</code> ， <code>nums[i]</code>&nbsp;的前一个元素是 <code>nums[(i - 1 + n) % n]</code> 。</p>
//
//<p><strong>子数组</strong> 最多只能包含固定缓冲区&nbsp;<code>nums</code>&nbsp;中的每个元素一次。形式上，对于子数组&nbsp;<code>nums[i], nums[i + 1], ..., nums[j]</code>&nbsp;，不存在&nbsp;<code>i &lt;= k1, k2 &lt;= j</code>&nbsp;其中&nbsp;<code>k1 % n == k2 % n</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,-2,3,-2]
//<strong>输出：</strong>3
//<strong>解释：</strong>从子数组 [3] 得到最大和 3
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [5,-3,5]
//<strong>输出：</strong>10
//<strong>解释：</strong>从子数组 [5,5] 得到最大和 5 + 5 = 10
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,-2,2,-3]
//<strong>输出：</strong>3
//<strong>解释：</strong>从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == nums.length</code></li> 
// <li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>-3 * 10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code>​​​​​​​</li> 
//</ul>
//
//<div><li>👍 421</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

// 918.环形子数组的最大和
// 开题时间：2022-11-23 12:13:08
public class MaximumSumCircularSubarray {
  public static void main(String[] args) {
    Solution solution = new MaximumSumCircularSubarray().new Solution();
    System.out.println(solution.maxSubarraySumCircular(new int[]{5, -3, 5}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maxSubarraySumCircularX(int[] nums) {
      int n = nums.length;
      int[] arr = new int[n * 2 - 1];
      System.arraycopy(nums, 0, arr, 0, n);
      System.arraycopy(nums, 0, arr, n, n - 1);
      
      int max = nums[0];
      for (int i = 0; i < n; i++) {
        for (int j = 0, cur = 0; j < n; j++) {
          int num = arr[i + j];
          cur = Math.max(cur + num, num);
          max = Math.max(max, cur);
        }
      }
      return max;
    }
    
    // max = sum == minArr ? maxArr : max(maxArr , sum - minArr)
    public int maxSubarraySumCircular8(int[] nums) {
      int n = nums.length;
      
      int max = nums[0];
      int sum = max;
      int min = Math.min(0, nums[0]);
      for (int i = 1, iMin = nums[0], iMax = iMin; i < n; i++) {
        int num = nums[i];
        sum += num;
        iMin = Math.min(iMin + num, num);
        iMax = Math.max(iMax + num, num);
        min = Math.min(min, iMin);
        max = Math.max(max, iMax);
      }
      return sum == min ? max : Math.max(max, sum - min);
    }
    
    // 整理一下
    public int maxSubarraySumCircular7(int[] nums) {
      int sum = 0;
      int min = 0;
      int max = Integer.MIN_VALUE;
      int iMin = 0;
      int iMax = 0;
      for (int num : nums) {
        sum += num;
        iMin = Math.min(iMin + num, num);
        iMax = Math.max(iMax + num, num);
        min = Math.min(min, iMin);
        max = Math.max(max, iMax);
      }
      return sum == min ? max : Math.max(max, sum - min);
    }
    
    // 最大最小值逻辑分开写，最小值不用考虑min[len-1]（因为这种情况和最大值的逻辑是重复的）。
    public int maxSubarraySumCircular(int[] nums) {
      int n = nums.length;
      
      int max = nums[0];
      int sum = max;
      int cur = max;
      for (int i = 1; i < n; i++) {
        int num = nums[i];
        sum += num;
        cur = Math.max(cur + num, num);
        max = Math.max(max, cur);
      }
      
      int min = 0;
      cur = 0;
      for (int i = 0; i < n - 1; i++) {
        int num = nums[i];
        cur = Math.min(cur + num, num);
        min = Math.min(min, cur);
      }
      
      return Math.max(max, sum - min);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}