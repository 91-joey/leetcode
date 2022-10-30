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
package org.example.leetcode.problems;

//34.在排序数组中查找元素的第一个和最后一个位置
//开题时间：2022-10-30 09:37:13
public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int len = nums.length;
            if (len == 0)
                return new int[]{-1, -1};

            int l = 0, r = len - 1, mid = 0;
            boolean found = false;
            while (l < r) {
                mid = l + r >> 1;
                if (nums[mid] == target) {
                    found = true;
                    break;
                } else if (nums[mid] < target)
                    l = mid + 1;
                else
                    r = mid - 1;
            }

            if (!found) {
                if (target == nums[l])
                    return new int[]{l, l};
                else
                    return new int[]{-1, -1};
            }

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
    }
//leetcode submit region end(Prohibit modification and deletion)
}