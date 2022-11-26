//<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，你可以对它进行一些操作。</p>
//
//<p>每次操作中，选择任意一个&nbsp;<code>nums[i]</code>&nbsp;，删除它并获得&nbsp;<code>nums[i]</code>&nbsp;的点数。之后，你必须删除 <strong>所有 </strong>等于&nbsp;<code>nums[i] - 1</code> 和 <code>nums[i] + 1</code>&nbsp;的元素。</p>
//
//<p>开始你拥有 <code>0</code> 个点数。返回你能通过这些操作获得的最大点数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,4,2]
//<strong>输出：</strong>6
//<strong>解释：</strong>
//删除 4 获得 4 个点数，因此 3 也被删除。
//之后，删除 2 获得 2 个点数。总共获得 6 个点数。
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,2,3,3,3,4]
//<strong>输出：</strong>9
//<strong>解释：</strong>
//删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
//之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
//总共获得 9 个点数。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 698</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//740.删除并获得点数
//开题时间：2022-11-26 15:57:16
public class DeleteAndEarn {
    public static void main(String[] args) {
        Solution solution = new DeleteAndEarn().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //还是打家劫舍系列，数组元素值为「元素值 * 出现次数」
        public int deleteAndEarn9(int[] nums) {
            int[] freq = new int[10001];
            for (int num : nums)
                freq[num]++;

            int pre = 0, cur = 0;
            for (int i = 0; i < 10001; i++) {
                int tmp = Math.max(cur, pre + i * freq[i]);
                pre = cur;
                cur = tmp;
            }
            return cur;
        }

        //☆☆☆☆☆ 优化
        public int deleteAndEarn(int[] nums) {
            int size = Arrays.stream(nums).max().getAsInt() + 1;
//            int size = 1;
//            for (int num : nums)
//                size = Math.max(size, num);

            int[] sum = new int[++size];
            for (int num : nums)
                sum[num] += num;

            int cur = 0;
            for (int i = 0, pre = 0; i < size; i++) {
                int tmp = Math.max(cur, pre + sum[i]);
                pre = cur;
                cur = tmp;
            }
            return cur;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}