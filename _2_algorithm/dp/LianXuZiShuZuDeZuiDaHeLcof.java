//<p>输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。</p>
//
//<p>要求时间复杂度为O(n)。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例1:</strong></p>
//
//<pre><strong>输入:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
//<strong>输出:</strong> 6
//<strong>解释:</strong>&nbsp;连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;=&nbsp;arr.length &lt;= 10^5</code></li> 
// <li><code>-100 &lt;= arr[i] &lt;= 100</code></li> 
//</ul>
//
//<p>注意：本题与主站 53 题相同：<a href="https://leetcode-cn.com/problems/maximum-subarray/">https://leetcode-cn.com/problems/maximum-subarray/</a></p>
//
//<p>&nbsp;</p>
//
//<div><li>👍 628</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

//剑指 Offer 42.连续子数组的最大和
//开题时间：2022-12-19 09:58:26
public class LianXuZiShuZuDeZuiDaHeLcof {
    public static void main(String[] args) {
        Solution solution = new LianXuZiShuZuDeZuiDaHeLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray9(int[] nums) {
            int n = nums.length + 1;
            int[] f = new int[n];

            int max = Integer.MIN_VALUE;
            for (int i = 1; i < n; i++) {
                f[i] = Math.max(f[i - 1] + nums[i - 1], nums[i - 1]);
                max = Math.max(max, f[i]);
            }

            return max;
        }

        public int maxSubArray(int[] nums) {
            int n = nums.length + 1;

            int max = Integer.MIN_VALUE;
            for (int i = 1, pre = 0; i < n; i++) {
                pre = Math.max(pre + nums[i - 1], nums[i - 1]);
                max = Math.max(max, pre);
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}