//<p>给你一个数组 <code>nums</code> 。<code>nums</code> 的源数组中，所有元素与 <code>nums</code> 相同，但按非递减顺序排列。</p>
//
//<p>如果&nbsp;<code>nums</code> 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 <code>true</code><em> </em>；否则，返回 <code>false</code> 。</p>
//
//<p>源数组中可能存在 <strong>重复项</strong> 。</p>
//
//<p><strong>注意：</strong>我们称数组 <code>A</code> 在轮转 <code>x</code> 个位置后得到长度相同的数组 <code>B</code> ，当它们满足 <code>A[i] == B[(i+x) % A.length]</code> ，其中 <code>%</code> 为取余运算。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,4,5,1,2]
//<strong>输出：</strong>true
//<strong>解释：</strong>[1,2,3,4,5] 为有序的源数组。
//可以轮转 x = 3 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,1,3,4]
//<strong>输出：</strong>false
//<strong>解释：</strong>源数组无法经轮转得到 nums 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3]
//<strong>输出：</strong>true
//<strong>解释：</strong>[1,2,3] 为有序的源数组。
//可以轮转 x = 0 个位置（即不轮转）得到 nums 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 30</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

//1752.检查数组是否经排序和轮转得到
//开题时间：2022-11-27 09:12:58
public class CheckIfArrayIsSortedAndRotated {
    public static void main(String[] args) {
        Solution solution = new CheckIfArrayIsSortedAndRotated().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean check(int[] nums) {
            int gt = 0;
            for (int i = 1; i < nums.length; i++)
                if (nums[i - 1] > nums[i])
                    if (gt++ == 1)
                        return false;
            return gt == 0 || nums[0] >= nums[nums.length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}