package org.example.leetcode.problems._9_contest.week329;

import java.util.Arrays;
import java.util.HashMap;

//
public class T4 {
    public static void main(String[] args) {
        Solution solution = new T4().new Solution();
        System.out.println(solution.minCost(new int[]{1, 2, 1, 2, 1, 3, 3}, 2));
    }

    class Solution {
        public int minCost(int[] nums, int k) {
            int n = nums.length;
            HashMap<Integer, Integer> map = new HashMap<>();
            int[][] trim = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (j > 0)
                        trim[i][j] = trim[i][j - 1];
                    Integer merge = map.merge(nums[j], 1, Integer::sum);
                    if (merge == 2)
                        trim[i][j] += 2;
                    else if (merge > 2)
                        trim[i][j]++;
                }
                map.clear();
            }

            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = k;
            for (int i = 1; i < n; i++) {
                dp[i] = trim[0][i] + k;
                for (int j = i - 1; j >= 0; j--) {
                    dp[i] = Math.min(dp[i], dp[j] + trim[j + 1][i] + k);
                }
            }
            return dp[n - 1];
        }

    }
}
