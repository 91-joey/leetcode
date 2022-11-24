//<p>给你一个按照非递减顺序排列的整数数组 <code>nums</code>，和一个目标值 <code>target</code>。请你找出给定目标值在数组中的开始位置和结束位置。</p>
//
//<p>如果数组中不存在目标值 <code>target</code>，返回&nbsp;<code>[-1, -1]</code>。</p>
//
//<p>你必须设计并实现时间复杂度为&nbsp;<code>O(log n)</code>&nbsp;的算法解决此问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [<span><code>5,7,7,8,8,10]</code></span>, target = 8
//<strong>输出：</strong>[3,4]</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [<span><code>5,7,7,8,8,10]</code></span>, target = 6
//<strong>输出：</strong>[-1,-1]</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [], target = 0
//<strong>输出：</strong>[-1,-1]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>9</sup>&nbsp;&lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code></li> 
// <li><code>nums</code>&nbsp;是一个非递减数组</li> 
// <li><code>-10<sup>9</sup>&nbsp;&lt;= target&nbsp;&lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 1974</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

import java.util.Arrays;

//34.在排序数组中查找元素的第一个和最后一个位置
//开题时间：2022-10-30 09:37:13
public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        System.out.println(Arrays.toString(solution.searchRange2(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int len = nums.length;
            int l = 0, r = len - 1, mid = 0;
            while (l <= r) {
                mid = l + r >> 1;
                if (nums[mid] == target)
                    break;
                else if (nums[mid] < target)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            if (l > r)
                return new int[]{-1, -1};

            int l1 = 0;
            int r1 = mid;
            while (l1 + 1 < r1) {
                int mid2 = l1 + r1 >> 1;
                if (nums[mid2] == nums[mid])
                    r1 = mid2;
                else
                    l1 = mid2;
            }


            int l2 = mid;
            int r2 = len - 1;
            while (l2 + 1 < r2) {
                int mid2 = l2 + r2 >> 1;
                if (nums[mid2] == nums[mid])
                    l2 = mid2;
                else
                    r2 = mid2;
            }

            return new int[]{
                    nums[l1] == nums[mid] ? l1 : r1,
                    nums[r2] == nums[mid] ? r2 : l2
            };
        }

        //三重二分（笨拙）
        public int[] searchRange2(int[] nums, int target) {
            int len = nums.length;
            int l = 0, r = len - 1, mid = 0;
            while (l <= r) {
                mid = l + r >> 1;
                if (nums[mid] == target)
                    break;
                else if (nums[mid] < target)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            if (l > r)
                return new int[]{-1, -1};

            int l1 = 0;
            int r1 = mid;
            while (l1 <= r1) {
                int mid2 = l1 + r1 >> 1;
                if (nums[mid2] == nums[mid])
                    r1 = mid2 - 1;
                else
                    l1 = mid2 + 1;
            }

            int l2 = mid;
            int r2 = len - 1;
            while (l2 <= r2) {
                int mid2 = l2 + r2 >> 1;
                if (nums[mid2] == nums[mid])
                    l2 = mid2 + 1;
                else
                    r2 = mid2 - 1;
            }

            return new int[]{l1, r2};
        }

        //☆☆☆☆☆ 二重二分（开始位置=第一个 >= target 的索引，结束位置=第一个 >=target + 1 的索引 - 1）
        public int[] searchRange3(int[] nums, int target) {
            int n = nums.length;

            int start = binarySearch(nums, 0, n, target);
            if (start == n || nums[start] != target)
                return new int[]{-1, -1};

            return new int[]{start, binarySearch(nums, start, n, target + 1) - 1};
        }

        public static int binarySearch(int[] nums, int l, int r, int target) {
            while (l < r) {
                int mid = ((r - l) >> 1) + l;
                if (target <= nums[mid])
                    r = mid;
                else
                    l = mid + 1;
            }
            return r;
        }

        //☆☆☆☆☆ 二重二分（开始位置=第一个 == target 的索引，结束位置=最后一个 ==target 的索引）
        public int[] searchRange4(int[] nums, int target) {
            int len = nums.length;

            int l = 0, r = len - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (target <= nums[mid])
                    r = mid;
                else
                    l = mid + 1;
            }
            if (r < 0 || target != nums[r])
                return new int[]{-1, -1};

            int L = r;
            r = len - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (target < nums[mid])
                    r = mid - 1;
                else
                    l = mid;
            }
            return new int[]{L, r};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}