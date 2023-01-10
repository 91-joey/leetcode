//<p>数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。</p>
//
//<p>&nbsp;</p>
//
//<p>你可以假设数组是非空的，并且给定的数组总是存在多数元素。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1:</strong></p>
//
//<pre><strong>输入:</strong> [1, 2, 3, 2, 2, 2, 5, 4, 2]
//<strong>输出:</strong> 2</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>限制：</strong></p>
//
//<p><code>1 &lt;= 数组长度 &lt;= 50000</code></p>
//
//<p>&nbsp;</p>
//
//<p>注意：本题与主站 169 题相同：<a href="https://leetcode-cn.com/problems/majority-element/">https://leetcode-cn.com/problems/majority-element/</a></p>
//
//<p>&nbsp;</p>
//
//<div><li>👍 336</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.Arrays;
import java.util.HashMap;

//剑指 Offer 39.数组中出现次数超过一半的数字
//开题时间：2023-01-10 11:25:07
public class ShuZuZhongChuXianCiShuChaoGuoYiBanDeShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new ShuZuZhongChuXianCiShuChaoGuoYiBanDeShuZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //哈希表   n   n
        public int majorityElement9(int[] nums) {
            HashMap<Integer, Integer> val2cnt = new HashMap<>();
            int t = nums.length / 2;
            for (int x : nums) {
                Integer cnt = val2cnt.get(x);
                if (cnt != null && cnt >= t)
                    return x;
                val2cnt.merge(x, 1, Integer::sum);
            }
            return nums[0];
        }

        //排序    nlogn   logn
        public int majorityElement8(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }

        //☆☆☆☆☆ 摩尔投票法 n   1
        public int majorityElement(int[] nums) {
            int x = 0, votes = 0;
            for (int num : nums) {
                if (votes == 0)
                    x = num;
                votes += num == x ? 1 : -1;
            }
            return x;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}