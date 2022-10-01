//<p>给你一个整数数组 <code>nums</code>，和一个整数 <code>k</code> 。</p>
//
//<p>在一个操作中，您可以选择 <code>0 &lt;= i &lt; nums.length</code> 的任何索引 <code>i</code> 。将 <code>nums[i]</code> 改为 <code>nums[i] + x</code> ，其中 <code>x</code> 是一个范围为 <code>[-k, k]</code> 的整数。对于每个索引 <code>i</code> ，最多 <strong>只能 </strong>应用 <strong>一次</strong> 此操作。</p>
//
//<p><code>nums</code>&nbsp;的&nbsp;<strong>分数&nbsp;</strong>是&nbsp;<code>nums</code>&nbsp;中最大和最小元素的差值。&nbsp;</p>
//
//<p><em>在对&nbsp; <code>nums</code> 中的每个索引最多应用一次上述操作后，返回&nbsp;<code>nums</code> 的最低 <strong>分数</strong></em> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1], k = 0
//<strong>输出：</strong>0
//<strong>解释：</strong>分数是 max(nums) - min(nums) = 1 - 1 = 0。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,10], k = 2
//<strong>输出：</strong>6
//<strong>解释：</strong>将 nums 改为 [2,8]。分数是 max(nums) - min(nums) = 8 - 2 = 6。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,3,6], k = 3
//<strong>输出：</strong>0
//<strong>解释：</strong>将 nums 改为 [4,4,4]。分数是 max(nums) - min(nums) = 4 - 4 = 0。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>数学</li></div></div><br><div><li>👍 168</li><li>👎 0</li></div>
package org.example.leetcode.problems.algorithm.sort.problems;

import java.util.Arrays;

//908.最小差值 I
//开题时间：2022-09-29 10:59:43
public class SmallestRangeI {
    public static void main(String[] args) {
        Solution solution = new SmallestRangeI().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //笨办法：排序
        public int smallestRangeI(int[] nums, int k) {
            int length = nums.length;
            if (length == 1) return 0;
            Arrays.sort(nums);
            return nums[length - 1] - nums[0] > 2 * k ? nums[length - 1] - nums[0] - 2 * k : 0;
        }

        //数学法（求最值）
        public int smallestRangeI2(int[] nums, int k) {
            int min = nums[0];
            int max = nums[0];
            for (int e : nums) {
                if (e < min) min = e;
                else if (max < e) max = e;
            }

            return Math.max(max - min - 2 * k, 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}