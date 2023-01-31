package org.example.leetcode.problems._9_contest.history.biweek96;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//2542. Maximum Subsequence Score
public class T3 {
    public static void main(String[] args) {
        Solution solution = new T3().new Solution();
        System.out.println(solution.maxScore(new int[]{1, 3, 3, 2}, new int[]{2, 1, 3, 4}, 3));
    }

    class Solution {
        public long maxScoreX(int[] nums1, int[] nums2, int k) {
            int n = nums1.length;
            int[][] data = new int[n][2];
            for (int i = 0; i < n; i++) {
                data[i][0] = nums1[i];
                data[i][1] = i;
            }
            Arrays.sort(data, (a, b) -> b[0] - a[0]);
            int min = Integer.MAX_VALUE;
            long res = 0;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (count == k) break;
                if (nums2[data[i][1]] < min) {
                    min = nums2[data[i][1]];
                    res += data[i][0];
                    count++;
                }
            }
            return res * min;
        }

        /*
         * ☆☆☆☆☆ 从大到小枚举 nums2，作为最小值（排序 + 最小堆 +枚举）
         * 1.将 nums1 和 nums2 一起按照 nums2 逆序排序，这里可以直接对索引进行排序
         * 2.先计算最大的最小值时的分数，并初始化 nums1 中元素的小根堆（固定大小为 k）
         * 3.从最大的最小值开始，从大到小枚举 nums2，作为最小值，设当前索引为 i，则 k - 1 个索引只能在 [0, i - 1] 之间选取。
         *      每次移出堆顶最小值，即可保证 nums1 中 k - 1 个元素和最大
         */
        public long maxScore(int[] nums1, int[] nums2, int k) {
            int n = nums1.length;
            Integer[] indices = new Integer[n];
            for (int i = 0; i < n; i++)
                indices[i] = i;
            Arrays.sort(indices, Comparator.comparingInt(i -> nums2[(int) i]).reversed());

            PriorityQueue<Integer> pq = new PriorityQueue<>(k);
            long sum = 0;
            for (int i = 0; i < k - 1; i++) {
                int num = nums1[indices[i]];
                sum += num;
                pq.offer(num);
            }

            long ans = 0;
            for (int i = k - 1; i < n; i++) {
                int num = nums1[indices[i]];
                sum += num;
                ans = Math.max(ans, sum * nums2[indices[i]]);

                pq.offer(num);
                //移出堆顶最小值，保证 nums1 中 k - 1 个元素和最大
                sum -= pq.poll();
            }

            return ans;
        }
    }
}
