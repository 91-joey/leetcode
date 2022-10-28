//<p>给定一个含有&nbsp;<code>n</code><strong>&nbsp;</strong>个正整数的数组和一个正整数 <code>target</code><strong> 。</strong></p>
//
//<p>找出该数组中满足其和<strong> </strong><code>≥ target</code><strong> </strong>的长度最小的 <strong>连续子数组</strong>&nbsp;<code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> ，并返回其长度<strong>。</strong>如果不存在符合条件的子数组，返回 <code>0</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>target = 7, nums = [2,3,1,2,4,3]
//<strong>输出：</strong>2
//<strong>解释：</strong>子数组&nbsp;<span><code>[4,3]</code></span>&nbsp;是该条件下的长度最小的子数组。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>target = 4, nums = [1,4,4]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>target = 11, nums = [1,1,1,1,1,1,1,1]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<ul> 
// <li>如果你已经实现<em> </em><code>O(n)</code> 时间复杂度的解法, 请尝试设计一个 <code>O(n log(n))</code> 时间复杂度的解法。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>前缀和</li><li>滑动窗口</li></div></div><br><div><li>👍 1401</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.Arrays;

//209.长度最小的子数组
//开题时间：2022-10-06 10:36:23
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        Solution solution = new MinimumSizeSubarraySum().new Solution();
//        System.out.println(solution.minSubArrayLen2(7, new int[]{2, 3, 1, 2, 4, 3}));
//        System.out.println(4);
//        System.out.println(~4);
        System.out.println(solution.minSubArrayLen5(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //滑动窗口  加法
        public int minSubArrayLen(int target, int[] nums) {
            int min = Integer.MAX_VALUE;
            for (int l = 0, r = 0, sum = 0; r < nums.length; ) {
                sum += nums[r++];
                if (sum >= target) {
                    do {
                        sum -= nums[l++];
                    } while (sum >= target);
                    min = Math.min(min, r - l + 1);
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }

        //☆☆☆ 滑动窗口  减法
        public int minSubArrayLen4(int target, int[] nums) {
            int min = Integer.MAX_VALUE;
            for (int l = 0, r = 0; r < nums.length; ) {
                //用减法代替加法，少占用一个变量
                target -= nums[r++];
                if (target <= 0) {
                    do {
                        target += nums[l++];
                    } while (target <= 0);
                    //窗口最小时，再求最小长度
                    min = Math.min(min, r - l + 1);
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }

        //☆☆☆☆☆ 滑动窗口  减法  窗口初始化后不会再扩大，只会缩小或不变
        /*
         * 1.r++      窗口扩大到 target <= 0
         * 2.l++      窗口缩小到 target >  0
         * 3.l++ r++  窗口不变到 target <= 0
         * 4.重复步骤2
         */
        public int minSubArrayLen5(int target, int[] nums) {
            int length = nums.length;
            int l = 0;
            int r = 0;
            //1.r++      窗口扩大到 target <= 0
            while (r < length && target > 0)
                target -= nums[r++];

            if (target > 0)
                return 0;

            while (true) {
                //2.l++      窗口缩小到 target >  0
                while (target <= 0)
                    target += nums[l++];
                if (r == length)
                    break;
                if (l == r) {
                    return 1;
                }
                //3.l++ r++  窗口不变到 target <= 0
                while (r < length && target > 0) {
                    target -= nums[r++];
                    target += nums[l++];
                }
            }

            return r - l + 1;
        }

        //前缀和 + 二分查找
        public int minSubArrayLen2(int target, int[] nums) {
            int length = nums.length;
            int[] sums = new int[length + 1];
            for (int i = 0, sum = 0; i < length; ) {
                sum += nums[i++];
                sums[i] = sum;
            }

            int min = Integer.MAX_VALUE;
            for (int l = 0; ; ) {
                int search = Arrays.binarySearch(sums, l + 1, length + 1, target + sums[l]);
                if (search == -1) {
                    return 1;
                } else if (search == -length - 2) {
                    break;
                } else if (search < -1) {
                    search = -search - 1;
                }
                l = Arrays.binarySearch(sums, l + 1, search, sums[search] - target);
                if (l < 0) {
                    l = -l - 1;
                }
                min = Math.min(min, search - l + 1);
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }

        //(优化)前缀和 + 二分查找
        public int minSubArrayLen3(int target, int[] nums) {
            int length = nums.length;
            int[] sums = new int[length + 1];
            //求前缀和
            for (int i = 0; i < length; i++)
                sums[i + 1] = sums[i] + nums[i];

            int min = Integer.MAX_VALUE;
            for (int l = 0, r = 0; ; ) {
                r = Arrays.binarySearch(sums, l + 1, length + 1, target + sums[l]);
                //没有比 1 再小的「子数组长度」了
                if (r == -l - 2) {
                    return 1;
                    //窗口扩大到极限，仍 < target，退出循环
                } else if (r == -length - 2) {
                    break;
                } else if (r < -1) {
                    //用取反位运算，代替 -r - 1 运算，返回 > target 的第一个元素下标
                    r = ~r;
                }

                l = Arrays.binarySearch(sums, l + 1, r, sums[r] - target);
                if (l < 0)
                    l = ~l;
                //窗口最小时，再求最小长度
                min = Math.min(min, r - l + 1);
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}