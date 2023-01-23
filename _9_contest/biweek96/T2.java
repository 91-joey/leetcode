package org.example.leetcode.problems._9_contest.biweek96;

import java.util.Arrays;

//2541. Minimum Operations to Make Array Equal II
public class T2 {
    public static void main(String[] args) {
        Solution solution = new T2().new Solution();
        System.out.println(solution.minOperations(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 8}, 0));
    }

    class Solution {
        /*
         * 转换题意 + 一次遍历
         * 设 `diff[i] = nums2[i] - nums1[i]`，则问题变成把每个 diff[i] 变成 0 的最小操作次数。
         * 可行的充要条件：
         *  - diff[i] 是 k 的倍数，即 diff % k == 0，由于涉及到除法、k 为 0 的情况需要特殊考虑：所有 diff[i] == 0
         *  - 由于「一个数 + k，另一个数 − k」这个操作不会影响整个 diff[i] 的和，所以如果 diff[i] 的和为 0
         */
        public long minOperations9(int[] nums1, int[] nums2, int k) {
            if (k == 0)
                return Arrays.equals(nums1, nums2) ? 0 : -1;

            long ans = 0, sumDiff = 0;
            for (int i = 0; i < nums1.length; i++) {
                int diff = nums2[i] - nums1[i];
                if (diff % k != 0)
                    return -1;
                sumDiff += diff;
                ans += Math.abs(diff) / k;
            }
            return sumDiff == 0 ? ans / 2 : -1;
        }

        /*
         * 转换题意 + 一次遍历（优化）
         * 设 `diff[i] = nums2[i] - nums1[i]`，则问题变成把每个 diff[i] 变成 0 的最小操作次数。
         * 可行的充要条件：
         *  - diff[i] 是 k 的倍数，即 diff % k == 0，由于涉及到除法、k 为 0 的情况需要特殊考虑：所有 diff[i] == 0
         *  - 由于「一个数 + k，另一个数 − k」这个操作不会影响整个 diff[i] 的和，所以如果 diff[i] 的和为 0
         *
         * 优化如下：
         *  - 维护两个变量：正差值和 sumDiffPos 、负差值和 sumDiffNeg，这样的话每次循环只会更新一个变量
         *  - 注意到每次循环都要计算操作次数，即需要 / k，事实上我们可以先计算和，最后再 / k
         */
        public long minOperations(int[] nums1, int[] nums2, int k) {
            if (k == 0)
                return Arrays.equals(nums1, nums2) ? 0 : -1;

            long sumDiffPos = 0, sumDiffNeg = 0;
            for (int i = 0; i < nums1.length; i++) {
                int diff = nums2[i] - nums1[i];
                if (diff % k != 0)
                    return -1;
                if (diff > 0)
                    sumDiffPos += diff;
                else
                    sumDiffNeg += diff;
            }
            return sumDiffPos + sumDiffNeg == 0 ? sumDiffPos / k : -1;
        }
    }
}
