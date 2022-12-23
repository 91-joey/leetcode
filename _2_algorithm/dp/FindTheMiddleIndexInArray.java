//<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，请你找到 <strong>最左边</strong>&nbsp;的中间位置&nbsp;<code>middleIndex</code>&nbsp;（也就是所有可能中间位置下标最小的一个）。</p>
//
//<p>中间位置&nbsp;<code>middleIndex</code>&nbsp;是满足&nbsp;<code>nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1]</code>&nbsp;的数组下标。</p>
//
//<p>如果&nbsp;<code>middleIndex == 0</code>&nbsp;，左边部分的和定义为 <code>0</code>&nbsp;。类似的，如果&nbsp;<code>middleIndex == nums.length - 1</code>&nbsp;，右边部分的和定义为&nbsp;<code>0</code>&nbsp;。</p>
//
//<p>请你返回满足上述条件 <strong>最左边</strong>&nbsp;的<em>&nbsp;</em><code>middleIndex</code>&nbsp;，如果不存在这样的中间位置，请你返回&nbsp;<code>-1</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>nums = [2,3,-1,<em><strong>8</strong></em>,4]
//<b>输出：</b>3
//<strong>解释：</strong>
//下标 3 之前的数字和为：2 + 3 + -1 = 4
//下标 3 之后的数字和为：4 = 4
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>nums = [1,-1,<em><strong>4</strong></em>]
//<b>输出：</b>2
//<strong>解释：</strong>
//下标 2 之前的数字和为：1 + -1 = 0
//下标 2 之后的数字和为：0
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<b>输入：</b>nums = [2,5]
//<b>输出：</b>-1
//<b>解释：</b>
//不存在符合要求的 middleIndex 。
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<b>输入：</b>nums = [<em><strong>1</strong></em>]
//<b>输出：</b>0
//<strong>解释：</strong>
//下标 0 之前的数字和为：0
//下标 0 之后的数字和为：0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
// <li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>注意：</strong>本题与主站 724 题相同：<a href="https://leetcode-cn.com/problems/find-pivot-index/" target="_blank">https://leetcode-cn.com/problems/find-pivot-index/</a></p>
//
//<div><li>👍 34</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Arrays;

//1991.找到数组的中间位置
//开题时间：2022-12-18 10:05:10
public class FindTheMiddleIndexInArray {
    public static void main(String[] args) {
        Solution solution = new FindTheMiddleIndexInArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMiddleIndex9(int[] nums) {
            int n = nums.length;
            int[] prefix = new int[n + 1];

            for (int i = 1; i <= n; i++)
                prefix[i] = prefix[i - 1] + nums[i - 1];

            int ans = -1;
            for (int i = n - 1, suf = 0; i >= 0; i--) {
                if (prefix[i] == suf)
                    ans = i;
                suf += nums[i];
            }

            return ans;
        }

        //☆☆☆☆☆
        public int findMiddleIndex(int[] nums) {
            for (int i = 0, pre = 0, suf = Arrays.stream(nums).sum(); i < nums.length; i++) {
                suf -= nums[i];
                if (pre == suf)
                    return i;
                pre += nums[i];
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}