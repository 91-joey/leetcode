package org.example.leetcode.problems._9_contest.biweek96;

import java.util.Arrays;

//
public class T3 {
    public static void main(String[] args) {
        Solution solution = new T3().new Solution();
        System.out.println(solution);
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

//        public long maxScore(int[] nums1, int[] nums2, int k) {
//            int n = nums1.length;
//            int[][] f = new int[n][k];
//            f[0][0] = nums1[0] * nums2[0];
//            for (int i = 0; i < n; i++) {
//
//            }
//        }
    }
}
