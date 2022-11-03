//<p>给定一个包含&nbsp;<code>n + 1</code> 个整数的数组&nbsp;<code>nums</code> ，其数字都在&nbsp;<code>[1, n]</code>&nbsp;范围内（包括 <code>1</code> 和 <code>n</code>），可知至少存在一个重复的整数。</p>
//
//<p>假设 <code>nums</code> 只有 <strong>一个重复的整数</strong> ，返回&nbsp;<strong>这个重复的数</strong> 。</p>
//
//<p>你设计的解决方案必须 <strong>不修改</strong> 数组 <code>nums</code> 且只用常量级 <code>O(1)</code> 的额外空间。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,3,4,2,2]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,1,3,4,2]
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li> 
// <li><code>nums.length == n + 1</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= n</code></li> 
// <li><code>nums</code> 中 <strong>只有一个整数</strong> 出现 <strong>两次或多次</strong> ，其余整数均只出现 <strong>一次</strong></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><b>进阶：</b></p>
//
//<ul> 
// <li>如何证明 <code>nums</code> 中至少存在一个重复的数字?</li> 
// <li>你可以设计一个线性级时间复杂度 <code>O(n)</code> 的解决方案吗？</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>位运算</li><li>数组</li><li>双指针</li><li>二分查找</li></div></div><br><div><li>👍 1949</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;
import java.util.HashSet;

//287.寻找重复数
//开题时间：2022-11-03 20:08:20
public class FindTheDuplicateNumber {
    public static void main(String[] args) {
        Solution solution = new FindTheDuplicateNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //TLE   n^2 1
        public int findDuplicate2(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++)
                for (int j = i + 1; j < nums.length; j++)
                    if (nums[i] == nums[j])
                        return nums[i];

            return -1;
        }

        //sort  nlogn+n 1
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 1; i++)
                if (nums[i] == nums[i + 1])
                    return nums[i];
            return -1;
        }

        //hashset   n   n
        public int findDuplicate3(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums)
                if (!set.add(num))
                    return num;
            return -1;
        }

        //位运算   n   1
        public int findDuplicate4(int[] nums) {
            int bits = 1 << 17;
            int mask = bits - 1;
            for (int i = 0; i < nums.length; i++) {
                int idx = nums[i] & mask;
                int pre = nums[idx];
                nums[idx] |= bits;
                if (pre == nums[idx])
                    return idx;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}