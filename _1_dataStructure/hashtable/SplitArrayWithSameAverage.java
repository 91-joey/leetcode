//<p>给定你一个整数数组
// <meta charset="UTF-8" />&nbsp;<code>nums</code></p>
//
//<p>我们要将
// <meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;数组中的每个元素移动到&nbsp;<code>A</code>&nbsp;数组 或者&nbsp;<code>B</code>&nbsp;数组中，使得&nbsp;<code>A</code>&nbsp;数组和
// <meta charset="UTF-8" />&nbsp;<code>B</code>&nbsp;数组不为空，并且
// <meta charset="UTF-8" />&nbsp;<code>average(A) == average(B)</code>&nbsp;。</p>
//
//<p>如果可以完成则返回<code>true</code>&nbsp;， 否则返回 <code>false</code>&nbsp;&nbsp;。</p>
//
//<p><strong>注意：</strong>对于数组
// <meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;, 
// <meta charset="UTF-8" />&nbsp;<code>average(arr)</code>&nbsp;是
// <meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;的所有元素除以
// <meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;长度的和。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [1,2,3,4,5,6,7,8]
//<strong>输出:</strong> true
//<strong>解释: </strong>我们可以将数组分割为 [1,4,5,8] 和 [2,3,6,7], 他们的平均值都是4.5。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [3,1]
//<strong>输出:</strong> false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 30</code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 167</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.Arrays;

//805.数组的均值分割
//开题时间：2022-11-14 11:08:36
public class SplitArrayWithSameAverage {
    public static void main(String[] args) {
        Solution solution = new SplitArrayWithSameAverage().new Solution();
        System.out.println(Solution.searchSumOfTwo(new int[]{1, 2, 3}, 100).length);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean splitArraySameAverage(int[] nums) {
            int len = nums.length;
            if (len == 1)
                return false;

            Arrays.sort(nums);
            int sum = Arrays.stream(nums).sum();

            for (int i = 1; i <= len >> 1; i++) {
                if ((sum * i) % len == 0 && splitArraySameAverageBySize(nums, 0, len, i, sum * i / len))
                    return true;
            }

            return false;
        }

        private boolean splitArraySameAverageBySize(int[] nums, int start, int end, int cnt, int target) {
            int sum = 0;
            for (int i = 0; i < cnt; i++)
                sum += nums[start + i];
            if (target < sum)
                return false;

            sum = 0;
            for (int i = 0; i < cnt; i++)
                sum += nums[end - 1 - i];
            if (target > sum)
                return false;

            if (cnt == 1)
                return Arrays.binarySearch(nums, start, end, target) >= 0;
            else if (cnt == 2) {
                return searchSumOfTwo(nums, start, end, target).length != 0;
            } else {
                for (int i = 0; i <= nums.length - cnt; i++) {
                    if (splitArraySameAverageBySize(nums, i + 1, end, cnt - 1, target - nums[i]))
                        return true;
                }
            }

            return false;
        }

        public static int[] searchSumOfTwo(int[] arr, int start, int end, int target) {
            end--;
            while (start < end) {
                int sum = arr[start] + arr[end];
                if (sum == target) {
                    return new int[]{start, end};
                } else if (sum > target) {
                    end--;
                } else {
                    start++;
                }
            }
            return new int[]{};
        }

        public static int[] searchSumOfTwo(int[] arr, int target) {
            return searchSumOfTwo(arr, 0, arr.length, target);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}