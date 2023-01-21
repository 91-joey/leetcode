package org.example.leetcode.problems._9_contest.biweek96;

import java.util.Arrays;

//
public class T2 {
    public static void main(String[] args) {
        Solution solution = new T2().new Solution();
        System.out.println(solution.minOperations(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 8},0));
    }

    class Solution {
        public long minOperations(int[] nums1, int[] nums2, int k) {
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
    }
}
