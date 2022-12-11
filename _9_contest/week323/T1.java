package org.example.leetcode.problems._9_contest.week323;

import java.util.Arrays;

//6257. Delete Greatest Value in Each Row
public class T1 {
    public static void main(String[] args) {
        System.out.println(deleteGreatestValue(new int[][]{
                {1, 2, 4},
                {3, 3, 1}
        }));
    }


    //模拟    n*m*n
    public static int deleteGreatestValue9(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid[0].length; i++) {
            int max = 0;
            for (int[] arr : grid) {
                int idx = 0;
                for (int j = 1; j < arr.length; j++)
                    if (arr[j] > arr[idx])
                        idx = j;
                max = Math.max(max, arr[idx]);
                arr[idx] = Integer.MIN_VALUE;
            }
            ans += max;
        }
        return ans;
    }

    //排序    m*nlogn+m*n = m*nlogn
    public static int deleteGreatestValue8(int[][] grid) {
        for (int[] arr : grid)
            Arrays.sort(arr);

        int ans = 0;
        for (int j = 0; j < grid[0].length; j++) {
            int max = 0;
            for (int i = 0; i < grid.length; i++)
                max = Math.max(max, grid[i][j]);
            ans += max;
        }
        return ans;
    }

    //☆☆☆☆☆ 排序（Stream求列最大值）
    public static int deleteGreatestValue(int[][] grid) {
        for (int[] arr : grid)
            Arrays.sort(arr);

        int ans = 0;
        for (int j = 0; j < grid[0].length; j++) {
            int finalJ = j;
            ans += Arrays.stream(grid)
                    .mapToInt(arr -> arr[finalJ])
                    .max()
                    .getAsInt();
        }
        return ans;
    }
}
