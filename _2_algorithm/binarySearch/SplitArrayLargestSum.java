//<p>给定一个非负整数数组 <code>nums</code> 和一个整数&nbsp;<code>m</code> ，你需要将这个数组分成&nbsp;<code>m</code><em>&nbsp;</em>个非空的连续子数组。</p>
//
//<p>设计一个算法使得这&nbsp;<code>m</code><em>&nbsp;</em>个子数组各自和的最大值最小。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [7,2,5,10,8], m = 2
//<strong>输出：</strong>18
//<strong>解释：</strong>
//一共有四种方法将 nums 分割为 2 个子数组。 
//其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,4,5], m = 2
//<strong>输出：</strong>9
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,4,4], m = 3
//<strong>输出：</strong>4
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li> 
// <li><code>1 &lt;= m &lt;= min(50, nums.length)</code></li> 
//</ul>
//
//<div><li>👍 752</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

import java.util.Arrays;

//410.分割数组的最大值
//开题时间：2022-11-08 22:53:35
public class SplitArrayLargestSum {
    public static void main(String[] args) {
        Solution solution = new SplitArrayLargestSum().new Solution();
//        System.out.println(solution.splitArray(new int[]{2, 3, 1, 2, 4, 3}, 5));
        System.out.println(solution.splitArray(new int[]{2, 3, 1, 1, 1, 1, 1}, 5));
//        System.out.println(solution.splitArray(new int[]{1, 4, 4}, 3));
//        System.out.println(solution.splitArray(new int[]{1, 2, 3, 4, 5}, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 二分
         *   l=max(nums),r=max(nums[i]+...+nums[i+len-(m-1)-1])
         *   求第一个 子数组和 <= mid 的个数为 >=k 的值，再计算最大值
         */
        public int splitArray(int[] nums, int k) {
            int l = Arrays.stream(nums).max().getAsInt();
            int r = 0;
            for (int i = 0; i < nums.length - k + 1; i++)
                r += nums[i];
            for (int i = 0, sum = r; i < k - 1; i++) {
                sum += nums[i + nums.length - k + 1] - nums[i];
                r = Math.max(r, sum);
            }

            for (int i = 1; i < nums.length; i++)
                nums[i] += nums[i - 1];

            while (l < r) {
                int mid = l + r >> 1;
                int cnt = cntOfSubArrayNoGreaterThanVal(nums, mid);
                if (cnt <= k)
                    r = mid;
                else
                    l = mid + 1;
            }

            return r;
        }

        private int cntOfSubArrayNoGreaterThanVal(int[] nums, int limit) {
            int cnt = 0;

            for (int l = 1, len = nums.length, r = len, tmp = limit; l < len; r = len) {
                while (l < r) {
                    int mid = l + r >> 1;
                    if (nums[mid] > limit)
                        r = mid;
                    else
                        l = mid + 1;
                }
                limit = tmp + nums[r - 1];
                cnt++;
            }

            return cnt;
        }

        //二分 + 贪心  n * log(sum-max)
        public int splitArray8(int[] nums, int k) {
            int l = 0, r = 0;
            for (int num : nums) {
                l = Math.max(l, num);
                r += num;
            }

            while (l < r) {
                int mid = l + r >> 1;
                if (cntOfSubArray(nums, mid) <= k)
                    r = mid;
                else
                    l = mid + 1;
            }

            return r;
        }

        private int cntOfSubArray(int[] nums, int limit) {
            int cnt = 1, sum = 0;
            for (int num : nums) {
                if (sum + num > limit) {
                    cnt++;
                    sum = num;
                } else {
                    sum += num;
                }
            }
            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}