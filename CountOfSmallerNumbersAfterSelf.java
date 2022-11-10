//<p>给你一个整数数组 <code>nums</code><em> </em>，按要求返回一个新数组&nbsp;<code>counts</code><em> </em>。数组 <code>counts</code> 有该性质： <code>counts[i]</code> 的值是&nbsp; <code>nums[i]</code> 右侧小于&nbsp;<code>nums[i]</code> 的元素的数量。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [5,2,6,1]
//<strong>输出：</strong><span><code>[2,1,1,0] 
//<strong>解释：</strong></code></span>
//5 的右侧有 <strong>2 </strong>个更小的元素 (2 和 1)
//2 的右侧仅有 <strong>1 </strong>个更小的元素 (1)
//6 的右侧有 <strong>1 </strong>个更小的元素 (1)
//1 的右侧有 <strong>0 </strong>个更小的元素
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1]
//<strong>输出：</strong>[0]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1,-1]
//<strong>输出：</strong>[0,0]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 911</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//315.计算右侧小于当前元素的个数
//开题时间：2022-11-10 18:24:19
public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        Solution solution = new CountOfSmallerNumbersAfterSelf().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力 TLE
        public List<Integer> countSmaller9(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int cnt = 0;
                for (int j = i + 1; j < nums.length; j++)
                    if (nums[i] > nums[j])
                        cnt++;
                nums[i] = cnt;
            }
            return Arrays.stream(nums).boxed().toList();
        }

        public List<Integer> countSmaller(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int cnt = 0;
                for (int j = i + 1; j < nums.length; j++)
                    if (nums[i] > nums[j])
                        cnt++;
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    nums[i] = cnt;
                    i++;
                }
                nums[i] = cnt;
            }

            ArrayList<Integer> ans = new ArrayList<>(nums.length);
            for (int num : nums)
                ans.add(num);
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}