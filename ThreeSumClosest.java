//<p>给你一个长度为 <code>n</code> 的整数数组&nbsp;<code>nums</code><em>&nbsp;</em>和 一个目标值&nbsp;<code>target</code>。请你从 <code>nums</code><em> </em>中选出三个整数，使它们的和与&nbsp;<code>target</code>&nbsp;最接近。</p>
//
//<p>返回这三个数的和。</p>
//
//<p>假定每组输入只存在恰好一个解。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1,2,1,-4], target = 1
//<strong>输出：</strong>2
//<strong>解释：</strong>与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,0,0], target = 1
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>3 &lt;= nums.length &lt;= 1000</code></li> 
// <li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li> 
// <li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 1271</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//16.最接近的三数之和
//开题时间：2022-10-23 21:11:07
public class ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new ThreeSumClosest().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //排序+双指针
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int min = nums[0] + nums[1] + nums[2];
            if (target <= min) return min;
            int len = nums.length;
            int max = nums[len - 1] + nums[len - 2] + nums[len - 3];
            if (target >= max) return max;

            int res = 0, curDiff;
            for (int i = 0, diff = Integer.MAX_VALUE; i < len - 2; ) {
                int t = target - nums[i];
                for (int l = i + 1, r = len - 1; l < r; ) {
                    int sum = nums[l] + nums[r];
                    if (sum == t)
                        return target;
                    else if (sum < t) {
                        curDiff = t - sum;
                        while (l < r && nums[l] == nums[++l]) l++;
                    } else {
                        curDiff = sum - t;
                        while (l < r && nums[r] == nums[--r]) r--;
                    }

                    if (diff > curDiff) {
                        res = sum + nums[i];
                        diff = curDiff;
                    }
                }
                while (i < len - 2 && nums[i] == nums[++i]) i++;
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}