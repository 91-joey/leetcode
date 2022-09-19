//<p>给定一个<strong>非空</strong>整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。</p>
//
//<p><strong>说明：</strong></p>
//
//<p>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre><strong>输入:</strong> [2,2,1]
//<strong>输出:</strong> 1
//</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre><strong>输入:</strong> [4,1,2,1,2]
//<strong>输出:</strong> 4</pre>
//
//<div><div>Related Topics</div><div><li>位运算</li><li>数组</li></div></div><br><div><li>👍 2555</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.hashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//136.只出现一次的数字
//开题时间：2022-09-04 09:51:02
public class SingleNumber {
    public static void main(String[] args) {
        Solution solution = new SingleNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.hashtable   n   n
        public int singleNumber(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num)) {
                    set.remove(num);
                } else {
                    set.add(num);
                }
            }
            for (Integer element : set)
                return element;
            return -1;
        }

        //2.sort nlogn  logn
        public int singleNumber2(int[] nums) {
            Arrays.sort(nums);
            int length = nums.length;
            for (int i = 0; i < length - 1; i += 2)
                if (nums[i] != nums[i + 1])
                    return nums[i];
            return nums[length - 1];
        }

        //GJ.xor n   1
        public int singleNumberGJ(int[] nums) {
            int ans = 0;
            for (int num : nums)
                ans ^= num;
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}