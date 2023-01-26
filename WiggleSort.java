//<p>给你一个的整数数组&nbsp;<code>nums</code>, 将该数组重新排序后使&nbsp;<code>nums[0] &lt;= nums[1] &gt;= nums[2] &lt;= nums[3]...</code>&nbsp;</p>
//
//<p>输入数组总是有一个有效的答案。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入：</strong><span><code>nums = [3,5,2,1,6,4]</code></span>
//<strong>输出：</strong>[3,5,1,6,2,4]
//<strong>解释：</strong>[1,6,2,5,3,4]也是有效的答案</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<b>输入：</b>nums = [6,6,5,6,3,8]
//<b>输出：</b>[6,6,5,6,3,8]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<p>
// <meta charset="UTF-8" /></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
// <li> <p>输入的&nbsp;<code>nums</code> 保证至少有一个答案。</p> </li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><b>进阶：</b>你能在&nbsp;<code>O(n)</code>&nbsp;时间复杂度下解决这个问题吗？</p>
//
//<div><li>👍 104</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//280.摆动排序
//开题时间：2023-01-26 18:26:15
public class WiggleSort {
    public static void main(String[] args) {
        Solution solution = new WiggleSort().new Solution();
        solution.wiggleSort(new int[]{3, 5, 2, 1, 6, 4});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void wiggleSort(int[] nums) {
            Arrays.sort(nums);
            int[] ans = new int[nums.length];
            for (int i = 0; i * 2 < nums.length; i++) {
                ans[i * 2] = nums[i];
            }
            for (int i = nums.length - 1 - (nums.length & 1), j = nums.length - 1; i >= 0; i -= 2, j--) {
                ans[i] = nums[j];
            }
            System.arraycopy(ans, 0, nums, 0, nums.length);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}