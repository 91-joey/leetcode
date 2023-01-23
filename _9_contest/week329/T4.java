package org.example.leetcode.problems._9_contest.week329;

import java.util.Arrays;
import java.util.HashMap;

//2547. Minimum Cost to Split an Array
public class T4 {
    public static void main(String[] args) {
        Solution solution = new T4().new Solution();
        System.out.println(solution.minCost(new int[]{1, 2, 1, 2, 1, 3, 3}, 2));
    }

    class Solution {
        //划分型dp + 预处理子数组中出现次数 > 1 的元素数量
        public int minCost9(int[] nums, int k) {
            int n = nums.length;
            HashMap<Integer, Integer> map = new HashMap<>();
            int[][] trim = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (j > i)
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

        /*
         * 划分型dp + 实时维护频率数组 freq 和 只出现 1 次的元素个数 uniq
         * 定义：f[i] 表示 [0,i-1] 的子数组的拆分数组的最小代价
         * 状态转移：f[i] = min{f[j] + i - j - uniq + k} , j=[0,i-1]
         *              = i + k + min{f[j]- j - uniq}  , j=[0,i-1]
         * 答案：f[n]
         */
        public int minCost8(int[] nums, int k) {
            int n = nums.length;

            int[] f = new int[n + 1];
            Arrays.fill(f, Integer.MAX_VALUE);
            f[0] = 0;
            int[] freq = new int[n];
            for (int i = 1, uniq = 0; i < n + 1; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (++freq[nums[j]] == 1)
                        uniq++;
                    else if (freq[nums[j]] == 2)
                        uniq--;
                    f[i] = Math.min(f[i], f[j] - j - uniq);
                }
                f[i] += i + k;
                Arrays.fill(freq, 0);
                uniq = 0;
            }
            return f[n];
        }


        /*
         * ☆☆☆☆☆ 划分型dp（优化） + 实时维护频率数组 freq 和 只出现 1 次的元素个数 uniq
         * 定义：f[i] 表示 [0,i-1] 的子数组的拆分数组的最小代价
         *      f'[i] = f[i] - i
         * 状态转移：f[i] = i + k + min{f[j]- j - uniq}  , j=[0,i-1]
         *         f[i] - i = k + min{f[j]- j - uniq}  , j=[0,i-1]
         *            f'[i] = k + min{f[j]    - uniq}  , j=[0,i-1]
         * 答案：f'[n] + n
         */
        public int minCost(int[] nums, int k) {
            int n = nums.length;

            int[] f = new int[n + 1];
            Arrays.fill(f, Integer.MAX_VALUE);
            f[0] = 0;
            int[] freq = new int[n];
            for (int i = 1, uniq = 0; i < n + 1; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (++freq[nums[j]] == 1)
                        uniq++;
                    else if (freq[nums[j]] == 2)
                        uniq--;
                    f[i] = Math.min(f[i], f[j] - uniq);
                }
                f[i] += k;
                Arrays.fill(freq, 0);
                uniq = 0;
            }
            return f[n] + n;
        }

        //todo 线段树
    }
}
