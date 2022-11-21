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
//<div><li>👍 5433</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

//53.最大子数组和
//开题时间：2022-11-11 18:32:53
public class MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 包含索引处的数组最大值=以索引为起点（包括索引）的子数组和最大值+以索引为终点（不包括索引）的子数组和最大值
         * 子数组和的最大值=max（索引处的数组最大值)
         */
        public int maxSubArray9(int[] nums) {
            int len = nums.length;
            int[] maxL = new int[len];
            int[] maxR = new int[len];
            for (int i = 1; i < len; i++) {
                maxL[i] = Math.max(nums[i - 1], 0);
                maxL[i] = Math.max(maxL[i], nums[i - 1] + maxL[i - 1]);
            }
            maxR[len - 1] = nums[len - 1];
            for (int i = len - 2; i >= 0; i--)
                maxR[i] = Math.max(nums[i], nums[i] + maxR[i + 1]);

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++)
                max = Math.max(max, maxL[i] + maxR[i]);
            return max;
        }

        public int maxSubArray8(int[] nums) {
            int len = nums.length;
            int[] maxR = new int[len];
            maxR[len - 1] = nums[len - 1];
            for (int i = len - 2; i >= 0; i--)
                maxR[i] = Math.max(nums[i], nums[i] + maxR[i + 1]);

            int max = Integer.MIN_VALUE;
            for (int i = 0, maxL = 0; i < len; i++) {
                max = Math.max(max, maxL + maxR[i]);
                int maxLPre = maxL;
                maxL = Math.max(nums[i], 0);
                maxL = Math.max(maxL, nums[i] + maxLPre);
            }
            return max;
        }

        /*
         * ☆☆☆☆☆ DP n   1
         * 子数组和的最大值=max（以索引为终点（包括终点）的数组最大值 f(i) )
         *      f(i)=max(nums[i],nums[i]+f(i-1))
         */
        public int maxSubArray7(int[] nums) {
            int max = nums[0];
            int cur = max;
            for (int i = 1; i < nums.length; i++) {
                cur = Math.max(nums[i], nums[i] + cur);
                max = Math.max(max, cur);
            }
            return max;
        }

        //分治（线段树）    n   logn
        public int maxSubArray(int[] nums) {
            return getStatus(nums, 0, nums.length - 1).max;
        }

        private Status getStatus(int[] nums, int start, int end) {
            if (start == end)
                return new Status(nums[start]);
            int mid = start + end >> 1;
            Status statusL = getStatus(nums, start, mid);
            Status statusR = getStatus(nums, mid + 1, end);
            return new Status(
                    Math.max(statusL.lMax, statusL.sum + statusR.lMax),
                    Math.max(statusR.rMax, statusR.sum + statusL.rMax),
                    Math.max(Math.max(statusL.max, statusR.max), statusL.rMax + statusR.lMax),
                    statusL.sum + statusR.sum
            );
        }

        class Status {
            int lMax, rMax, max, sum;

            public Status(int single) {
                this.lMax = single;
                this.rMax = single;
                this.max = single;
                this.sum = single;
            }

            public Status(int lMax, int rMax, int max, int sum) {
                this.lMax = lMax;
                this.rMax = rMax;
                this.max = max;
                this.sum = sum;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}