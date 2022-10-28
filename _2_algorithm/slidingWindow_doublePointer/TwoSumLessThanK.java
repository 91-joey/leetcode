//<p>给你一个整数数组 <code>nums</code> 和整数 <code>k</code> ，返回最大和 <code>sum</code> ，满足存在 <code>i &lt; j</code> 使得 <code>nums[i] + nums[j] = sum</code> 且 <code>sum &lt; k</code> 。如果没有满足此等式的 <code>i,j</code> 存在，则返回 <code>-1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [34,23,1,24,75,33,54,8], k = 60
//<strong>输出：</strong>58
//<strong>解释：</strong>
//34 和 24 相加得到 58，58 小于 60，满足题意。
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [10,20,30], k = 15
//<strong>输出：</strong>-1
//<strong>解释：</strong>
//我们无法找到和小于 15 的两个元素。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 1000</code></li> 
// <li><code>1 &lt;= k &lt;= 2000</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>二分查找</li><li>排序</li></div></div><br><div><li>👍 71</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.Arrays;

//1099.小于 K 的两数之和
//开题时间：2022-10-28 09:13:38
public class TwoSumLessThanK {
    public static void main(String[] args) {
        Solution solution = new TwoSumLessThanK().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int twoSumLessThanK(int[] nums, int k) {
            int len = nums.length;
            if (len == 1) return -1;

            Arrays.sort(nums);
            if (nums[0] + nums[1] >= k)
                return -1;
            else if (nums[len - 2] + nums[len - 1] < k)
                return nums[len - 2] + nums[len - 1];

            int max = 2;
            for (int l = 0, r = len - 1; l < r; ) {
                int sum = nums[l] + nums[r];
                if (sum < k) {
                    max = Math.max(max, sum);
                    l++;
                } else
                    r--;
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}