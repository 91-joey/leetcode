package org.example.leetcode.problems._9_contest.week323;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class T2 {
    public static void main(String[] args) {

    }

    public int longestSquareStreakX(int[] nums) {
        Arrays.sort(nums);

        int max = 1;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++)
                if (nums[j] * nums[j] == nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            max = Math.max(max, dp[i]);
        }
        return max == 1 ? -1 : max;
    }

    public int longestSquareStreak(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);

        int max = -1;
        for (int num : nums) {
            int cnt = 1;
            for (int target = num * num; set.contains(target); target *= target) {
                cnt++;
            }
            max = Math.max(max, cnt);
        }
        return max == 1 ? -1 : max;
    }
}
