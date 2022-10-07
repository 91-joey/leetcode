//<p>给定一个二进制数组 <code>nums</code> ，如果最多可以翻转一个 <code>0</code> ，则返回数组中连续 <code>1</code> 的最大个数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,0,1,1,0]
//<strong>输出：</strong>4
//<strong>解释：</strong>翻转第一个 0 可以得到最长的连续 1。
//&nbsp;    当翻转以后，最大连续 1 的个数为 4。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<b>输入：</b>nums = [1,0,1,1,0,1]
//<b>输出：</b>4
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>nums[i]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code>.</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>如果输入的数字是作为<strong> 无限流 </strong>逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？</p>
//
//<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>滑动窗口</li></div></div><br><div><li>👍 115</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//487.最大连续1的个数 II
//开题时间：2022-10-07 11:20:11
public class MaxConsecutiveOnesIi {
    public static void main(String[] args) {
        Solution solution = new MaxConsecutiveOnesIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 1.r++ until 'second' 0 comes
         * 2.l++ r++ until r - l > sum + 1
         * 3.run step 1
         */
        public int findMaxConsecutiveOnes(int[] nums) {
            int l = 0;
            int r = 0;
            for (int sum = 0; r < nums.length; r++) {
                sum += nums[r];
                if (r - l > sum)
                    sum -= nums[l++];
            }
            return r - l;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}