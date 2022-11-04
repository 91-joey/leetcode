//<p>给定两个大小分别为 <code>m</code> 和 <code>n</code> 的正序（从小到大）数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>。请你找出并返回这两个正序数组的 <strong>中位数</strong> 。</p>
//
//<p>算法的时间复杂度应该为 <code>O(log (m+n))</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [1,3], nums2 = [2]
//<strong>输出：</strong>2.00000
//<strong>解释：</strong>合并数组 = [1,2,3] ，中位数 2
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [1,2], nums2 = [3,4]
//<strong>输出：</strong>2.50000
//<strong>解释：</strong>合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//</pre>
//
//<p>&nbsp;</p>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>nums1.length == m</code></li> 
// <li><code>nums2.length == n</code></li> 
// <li><code>0 &lt;= m &lt;= 1000</code></li> 
// <li><code>0 &lt;= n &lt;= 1000</code></li> 
// <li><code>1 &lt;= m + n &lt;= 2000</code></li> 
// <li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>分治</li></div></div><br><div><li>👍 6036</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//4.寻找两个正序数组的中位数
//开题时间：2022-11-04 18:03:30
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{0,0,0,0,0},new int[]{-1,0,0,0,0,0,1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力排序    (m+n)log(m+n)
        public double findMedianSortedArrays9(int[] nums1, int[] nums2) {
            int size = nums1.length + nums2.length;
            int[] merged = new int[size];
            System.arraycopy(nums1, 0, merged, 0, nums1.length);
            System.arraycopy(nums2, 0, merged, nums1.length, nums2.length);
            Arrays.sort(merged);
            int mid = size >> 1;
            return (size & 1) == 1 ?
                    merged[mid] :
                    //4 0,1,2,3
                    (merged[mid - 1] + merged[mid]) / 2.0;
        }

        //双指针排序 m+n
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int size = nums1.length + nums2.length;
            int[] merged = new int[size];
            for (int i = 0, l = 0, r = 0; i < size; i++) {
                if (r >= nums2.length) {
                    merged[i] = nums1[l++];
                } else if (l >= nums1.length) {
                    merged[i] = nums2[r++];
                } else if (nums1[l] <= nums2[r]) {
                    merged[i] = nums1[l++];
                } else {
                    merged[i] = nums2[r++];
                }
            }

            int mid = size >> 1;
            return (size & 1) == 1 ?
                    merged[mid] :
                    //4 0,1,2,3
                    (merged[mid - 1] + merged[mid]) / 2.0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}