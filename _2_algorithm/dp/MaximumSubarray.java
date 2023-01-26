//<p>给你一个整数数组 <code>nums</code> ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。</p>
//
//<p><strong>子数组 </strong>是数组中的一个连续部分。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-2,1,-3,4,-1,2,1,-5,4]
//<strong>输出：</strong>6
//<strong>解释：</strong>连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [5,4,-1,7,8]
//<strong>输出：</strong>23
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>如果你已经实现复杂度为 <code>O(n)</code> 的解法，尝试使用更为精妙的 <strong>分治法</strong> 求解。</p>
//
//<div><li>👍 5462</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Arrays;

//53.最大子数组和
//开题时间：2022-11-21 17:50:11
public class MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp
        public int maxSubArray9(int[] nums) {
            int n = nums.length;
            int[] f = new int[n + 1];
            for (int i = 1; i < n + 1; i++)
                f[i] = Math.max(f[i - 1], 0) + nums[i - 1];
            return Arrays.stream(f).skip(1).max().getAsInt();
        }

        //☆☆☆☆☆ dp 优化
        public int maxSubArray8(int[] nums) {
            int ans = Integer.MIN_VALUE;
            for (int i = 0, pre = 0; i < nums.length; i++) {
                pre = Math.max(pre, 0) + nums[i];
                ans = Math.max(ans, pre);
            }
            return ans;
        }

        public int maxSubArray(int[] nums) {
            int max = nums[0];
            for (int i = 1, maxPre = max; i < nums.length; i++) {
                maxPre = Math.max(nums[i], maxPre + nums[i]);
                max = Math.max(max, maxPre);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}