//<p>给你一个整数数组&nbsp;<code>arr</code> 和一个整数值&nbsp;<code>target</code>&nbsp;。</p>
//
//<p>请你在 <code>arr</code>&nbsp;中找 <strong>两个互不重叠的子数组</strong>&nbsp;且它们的和都等于&nbsp;<code>target</code>&nbsp;。可能会有多种方案，请你返回满足要求的两个子数组长度和的 <strong>最小值</strong> 。</p>
//
//<p>请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 <strong>-1</strong>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>arr = [3,2,2,4,3], target = 3
//<strong>输出：</strong>2
//<strong>解释：</strong>只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>arr = [7,3,4,7], target = 7
//<strong>输出：</strong>2
//<strong>解释：</strong>尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和 [7]），但我们会选择第一个和第三个子数组，因为它们的长度和 2 是最小值。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>arr = [4,3,2,6,2,3,4], target = 6
//<strong>输出：</strong>-1
//<strong>解释：</strong>我们只有一个和为 6 的子数组。
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>arr = [5,5,4,4,5], target = 3
//<strong>输出：</strong>-1
//<strong>解释：</strong>我们无法找到和为 3 的子数组。
//</pre>
//
//<p><strong>示例 5：</strong></p>
//
//<pre><strong>输入：</strong>arr = [3,1,1,1,5,1,2,1], target = 3
//<strong>输出：</strong>3
//<strong>解释：</strong>注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= arr.length &lt;= 10^5</code></li> 
// <li><code>1 &lt;= arr[i] &lt;= 1000</code></li> 
// <li><code>1 &lt;= target &lt;= 10^8</code></li> 
//</ul>
//
//<div><li>👍 121</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Arrays;
import java.util.HashMap;

//1477.找两个和为目标值且不重叠的子数组
//开题时间：2022-12-18 12:09:11
public class FindTwoNonOverlappingSubArraysEachWithTargetSum {
    public static void main(String[] args) {
        Solution solution = new FindTwoNonOverlappingSubArraysEachWithTargetSum().new Solution();
//        System.out.println(solution.minSumOfLengths(new int[]{1, 6, 1}, 7));
//        System.out.println(solution.minSumOfLengths(new int[]{3, 1, 1, 1, 5, 1, 2, 1}, 3));
//        System.out.println(solution.minSumOfLengths(new int[]{4,3,2,6,2,3,4}, 6));
        System.out.println(solution.minSumOfLengths(new int[]{4, 1, 1, 1, 4, 2, 1, 4, 3, 4}, 3));
//        System.out.println(solution.minSumOfLengths(new int[]{3, 2, 2, 4, 3}, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSumOfLengths9(int[] arr, int target) {
            int n = arr.length;
            int[] pre = new int[n];
            int[] suf = new int[n];

            HashMap<Integer, Integer> sum2idx = new HashMap<>();
            sum2idx.put(0, -1);
            for (int i = 0, sum = 0, min = 0; i < n - 1; i++) {
                sum += arr[i];
                pre[i] = i - sum2idx.getOrDefault(sum - target, i);
                if (min > 0 && pre[i] > 0) {
                    pre[i] = Math.min(pre[i], min);
                    min = pre[i];
                } else if (min > 0) {
                    pre[i] = min;
                } else
                    min = pre[i];

                sum2idx.put(sum, i);
            }

            sum2idx.clear();
            sum2idx.put(0, n);
            for (int i = n - 1, sum = 0, min = 0; i >= 0; i--) {
                sum += arr[i];
                suf[i] = sum2idx.getOrDefault(sum - target, i) - i;
                if (min > 0 && suf[i] > 0) {
                    suf[i] = Math.min(suf[i], min);
                    min = suf[i];
                } else if (min > 0) {
                    suf[i] = min;
                } else
                    min = suf[i];

                sum2idx.put(sum, i);
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n - 1; i++) {
                if (pre[i] > 0 && suf[i + 1] > 0)
                    min = Math.min(min, pre[i] + suf[i + 1]);
            }

            return min == Integer.MAX_VALUE ? -1 : min;
        }

        public int minSumOfLengths8(int[] arr, int target) {
            int n = arr.length + 1;
            int[] pre = new int[n];
            int[] suf = new int[n];
            pre[0] = suf[n - 1] = Integer.MAX_VALUE;

            HashMap<Integer, Integer> sum2idx = new HashMap<>();
            sum2idx.put(0, 0);
            for (int i = 1, sum = 0; i < n; i++) {
                sum += arr[i - 1];
                pre[i] = i - sum2idx.getOrDefault(sum - target, i - Integer.MAX_VALUE);
                pre[i] = Math.min(pre[i], pre[i - 1]);

                sum2idx.put(sum, i);
            }

            sum2idx.clear();
            sum2idx.put(0, n - 1);
            Arrays.fill(suf, Integer.MAX_VALUE);
            for (int i = n - 2, sum = 0; i > 0; i--) {
                sum += arr[i];
                Integer idx = sum2idx.get(sum - target);
                if (idx != null)
                    suf[i] = Math.min(idx - i, suf[i + 1]);

                sum2idx.put(sum, i);
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n - 1; i++)
                if (pre[i] < Integer.MAX_VALUE && suf[i] < Integer.MAX_VALUE)
                    min = Math.min(min, pre[i] + suf[i]);

            return min == Integer.MAX_VALUE ? -1 : min;
        }

        /*
         * ☆☆☆☆☆ DP + 双指针
         * 「数组元素为正整数」这个条件，可以用双指针（固定右端，左端右移）
         * `f[i]` 表示数组 [0,i) 范围内子数组长度和为 `target` 的最小值
         * DP数组存储的是最小长度，双指针又确定了分割点（`left`），因此可以很方便的找到「当前满足条件的子数组」的左边是否有「满足条件的子数组」及其长度
         */
        public int minSumOfLengths(int[] arr, int target) {
            int n = arr.length + 1;
            int[] f = new int[n];
            f[0] = n;

            int ans = n;
            for (int l = 0, r = 0, sum = 0; r < n - 1; r++) {
                sum += arr[r];
                while (sum > target)
                    sum -= arr[l++];

                if (sum == target) {
                    int len = r - l + 1;
                    ans = Math.min(ans, len + f[l]);
                    f[r + 1] = Math.min(f[r], len);
                } else
                    f[r + 1] = f[r];
            }

            return ans >= n ? -1 : ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}