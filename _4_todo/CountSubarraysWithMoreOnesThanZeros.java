//<p>给你一个只包含 <code>0</code> 和 <code>1</code> 的数组 <code>nums</code>，请返回 <code>1</code> 的数量 <strong>大于 </strong><code>0</code> 的数量的子数组的个数。由于答案可能很大，请返回答案对&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;<strong>取余</strong>&nbsp;的结果。</p>
//
//<p>一个 <strong>子数组</strong> 指的是原数组中连续的一个子序列。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre><strong>输入:</strong> nums = [0,1,1,0,1]
//<strong>输出:</strong> 9
//<strong>解释:</strong>
//长度为 1 的、1 的数量大于 0 的数量的子数组有: [1], [1], [1]
//长度为 2 的、1 的数量大于 0 的数量的子数组有: [1,1]
//长度为 3 的、1 的数量大于 0 的数量的子数组有: [0,1,1], [1,1,0], [1,0,1]
//长度为 4 的、1 的数量大于 0 的数量的子数组有: [1,1,0,1]
//长度为 5 的、1 的数量大于 0 的数量的子数组有: [0,1,1,0,1]
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre><strong>输入:</strong> nums = [0]
//<strong>输出:</strong> 0
//<strong>解释:</strong>
//没有子数组的 1 的数量大于 0 的数量。
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre><strong>输入:</strong> nums = [1]
//<strong>输出:</strong> 1
//<strong>解释:</strong>
//长度为 1 的、1 的数量大于 0 的数量的子数组有: [1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 1</code></li> 
//</ul>
//
//<div><li>👍 16</li><li>👎 0</li></div>
package org.example.leetcode.problems._4_todo;

//2031.1 比 0 多的子数组个数
//开题时间：2022-11-27 15:48:23
public class CountSubarraysWithMoreOnesThanZeros {
    public static void main(String[] args) {
        Solution solution = new CountSubarraysWithMoreOnesThanZeros().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int MOD = 10_0000_0007;

        public int subarraysWithMoreZerosThanOnes(int[] nums) {
            int cnt = 0;
            int cnt1 = 0;
            int cnt2 = 0;
            for (int num : nums) {
                if (num == 0)
                    cnt1 = cnt2;
                else
                    cnt1++;
                cnt += cnt1;
            }
            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}