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
package org.example.leetcode.problems._2_algorithm.binarySearch;

import java.util.Arrays;

//4.寻找两个正序数组的中位数
//开题时间：2022-11-04 18:03:30
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
//        System.out.println(solution.findMedianSortedArrays(new int[]{0, 0, 0, 0, 0}, new int[]{-1, 0, 0, 0, 0, 0, 1}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{}, new int[]{1}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{2}, new int[]{}));
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2, 5}, new int[]{3, 4, 6}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 5, 6}, new int[]{2, 3, 4}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{1, 1}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 7}));
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

        //双指针排序  m+n    m+n
        public double findMedianSortedArrays8(int[] nums1, int[] nums2) {
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

        //双指针（不排序）  m+n>>1  1
        public double findMedianSortedArrays7(int[] nums1, int[] nums2) {
            int size = nums1.length + nums2.length;
            int i = 0, j = 0, first = 0, second = 0;
            int mid = size >> 1;
            while (i + j <= mid) {
                first = second;
                if (j >= nums2.length || (i < nums1.length && nums1[i] <= nums2[j]))
                    second = nums1[i++];
                else
                    second = nums2[j++];
            }
            return (size & 1) == 1 ?
                    second :
                    (first + second) / 2.0;
        }

        //☆☆☆ 二分    log((m+n)/2)  1
        public double findMedianSortedArrays6(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            int second = (m + n >> 1) + 1;
            double secondTh = getKth(nums1, 0, m - 1, nums2, 0, n - 1, second);
            return (((m + n) & 1) == 1) ?
                    secondTh :
                    (getKth(nums1, 0, m - 1, nums2, 0, n - 1, second - 1) + secondTh) * 0.5;
        }

        private double getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            int len1 = end1 - start1 + 1;
            int len2 = end2 - start2 + 1;
            if (len1 == 0) return nums2[start2 + k - 1];
            else if (len2 == 0) return nums1[start1 + k - 1];
            if (k == 1) return Math.min(nums1[start1], nums2[start2]);

            int i = start1 + Math.min(k >> 1, len1) - 1;
            int j = start2 + Math.min(k >> 1, len2) - 1;
            if (nums1[i] < nums2[j])
                return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
            else
                return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }

        //自解二分（乱七八糟）    log(m+n)    1
        public double findMedianSortedArrays5(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            if (m < n) return findMedianSortedArrays8(nums2, nums1);
            if (n == 0) return (m & 1) == 1 ?
                    nums1[m >> 1] :
                    (nums1[(m >> 1) - 1] + nums1[m >> 1]) / 2.0;

            int target = m + n >> 1;
            int l = m - n >> 1, r = Math.min(m - 1, target);
            double median = getMedian(nums1, nums2, m, n, target, l, r);
            if (median != Double.MIN_VALUE) return median;
            l = 0;
            r = n - 1;
            return getMedian(nums2, nums1, m, n, target, l, r);
        }

        private double getMedian(int[] nums1, int[] nums2, int m, int n, int target, int l, int r) {
            while (l <= r) {
                int mid = l + r >> 1;
                if (target - mid < nums2.length && nums2[target - mid] < nums1[mid])
                    r = mid - 1;
                else if (target - mid - 1 >= 0 && nums2[target - mid - 1] > nums1[mid])
                    l = mid + 1;
                else {
                    if (((m + n) & 1) == 0) {
                        int neighbor;
                        if (mid == 0)
                            neighbor = nums2[target - mid - 1];
                        else if (target - mid == 0)
                            neighbor = nums1[mid - 1];
                        else
                            neighbor = Math.max(nums1[mid - 1], nums2[target - mid - 1]);
                        return (nums1[mid] + neighbor) / 2.0;
                    } else
                        return nums1[mid];
                }
            }
            return Double.MIN_VALUE;
        }

        public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            if (m > n) return findMedianSortedArrays4(nums2, nums1);

            boolean odd = ((m + n) & 1) == 1;
            if (m == 0) {
                return odd ?
                        nums2[n >> 1] :
                        (nums2[(n >> 1) - 1] + nums2[n >> 1]) * 0.5;
            } else if (nums1[m - 1] <= nums2[(m + n + 1 >> 1) - m]) {
                int second = (m + n + 1 >> 1) - m - 1 < 0 ? nums1[m - 1] : Math.max(nums1[m - 1], nums2[(m + n + 1 >> 1) - m - 1]);
                return odd ?
                        second :
                        (second + nums2[(m + n + 1 >> 1) - m]) * 0.5;
            } else if (nums2[(m + n + 1 >> 1) - 1] <= nums1[0]) {
                return odd ?
                        nums2[(m + n + 1 >> 1) - 1] :
                        (nums2[(m + n + 1 >> 1) - 1] + (m + n + 1 >> 1 >= n ? nums1[0] : Math.min(nums1[0], nums2[m + n + 1 >> 1]))) * 0.5;
            }

            int l = 1, r = m - 1;
            int mid = 1;
            while (l <= r) {
                mid = l + r >> 1;
                if (nums1[mid - 1] > nums2[(m + n + 1 >> 1) - mid])
                    r = mid - 1;
                else if (nums1[mid] < nums2[(m + n + 1 >> 1) - mid - 1])
                    l = mid + 1;
                else
                    break;
            }
            int second = Math.max(nums1[mid - 1], nums2[(m + n + 1 >> 1) - mid - 1]);
            return odd ?
                    second :
                    (second + Math.min(nums1[mid], nums2[(m + n + 1 >> 1) - mid])) * 0.5;
        }

        //☆☆☆☆☆ 二分 log(min(m,n))
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            if (m > n) return findMedianSortedArrays(nums2, nums1);

            for (int l = 0, r = m; l <= r; ) {
                int i = l + r >> 1;
                int j = (m + n + 1 >> 1) - i;
                if (i != 0 && j != n && nums1[i - 1] > nums2[j])
                    r = i - 1;
                else if (i != m && j != 0 && nums1[i] < nums2[j - 1])
                    l = i + 1;
                else {
                    int leftMax;
                    if (i == 0)
                        leftMax = nums2[j - 1];
                    else if (j == 0)
                        leftMax = nums1[i - 1];
                    else
                        leftMax = Math.max(nums1[i - 1], nums2[j - 1]);
                    if (((m + n) & 1) == 1)
                        return leftMax;

                    int rightMin;
                    if (i == m)
                        rightMin = nums2[j];
                    else if (j == n)
                        rightMin = nums1[i];
                    else
                        rightMin = Math.min(nums1[i], nums2[j]);
                    return (leftMax + rightMin) * 0.5;
                }
            }

            return 0.0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}