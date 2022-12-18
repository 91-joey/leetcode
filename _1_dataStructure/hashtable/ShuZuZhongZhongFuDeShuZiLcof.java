//<p>找出数组中重复的数字。</p>
//
//<p><br> 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。</br></p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>
//[2, 3, 1, 0, 2, 5, 3]
//<strong>输出：</strong>2 或 3 
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>限制：</strong></p>
//
//<p><code>2 &lt;= n &lt;= 100000</code></p>
//
//<div><li>👍 1022</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.HashSet;

//剑指 Offer 03.数组中重复的数字
//开题时间：2022-12-18 09:10:20
public class ShuZuZhongZhongFuDeShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new ShuZuZhongZhongFuDeShuZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findRepeatNumber9(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (int x : nums)
                if (!set.add(x))
                    return x;
            return -1;
        }

        //☆☆☆☆☆ 原地交换
        public int findRepeatNumber(int[] nums) {
            for (int i = 0; i < nums.length; )
                if (i == nums[i])
                    i++;
                else if (nums[nums[i]] == nums[i])
                    return nums[i];
                else
                    swap(nums, i, nums[i]);
            return -1;
        }

        public static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}