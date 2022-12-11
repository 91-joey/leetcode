package org.example.leetcode.problems._9_contest.week323;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;

public class T1 {
    public static void main(String[] args) {
        System.out.println(deleteGreatestValue(new int[][]{
                {1, 2, 4},
                {3, 3, 1}
        }));
    }


    public static int deleteGreatestValue(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid[0].length; i++) {
            int max = 0;
            for (int[] arr : grid) {
                int idx = 0;
                for (int j = 1; j < arr.length; j++) {
                    if (arr[j] > arr[idx])
                        idx = j;
                }
                max = Math.max(max, arr[idx]);
                arr[idx] = Integer.MIN_VALUE;
            }
            ans += max;
        }
        return ans;
    }
}
