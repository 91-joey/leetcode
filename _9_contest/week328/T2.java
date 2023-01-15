package org.example.leetcode.problems._9_contest.week328;

public class T2 {
    public static void main(String[] args) {
    }

    public static int[][] rangeAddQueriesX(int n, int[][] queries) {
        int[] diff = new int[n * n + 1];
        for (int[] query : queries) {
            int start = query[0] * n + query[1] + 1;
            int end = query[2] * n + query[3] + 1;
            diff[start]++;
            diff[end]--;
        }
        for (int i = 1; i < n * n; i++) {
            diff[i] = diff[i] + diff[i - 1];
        }
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = diff[i * n + j + 1];
            }
        }
        return arr;
    }

    public static int[][] rangeAddQueries9(int n, int[][] queries) {
        int[][] diff = new int[n][n + 2];
        for (int[] query : queries) {
            for (int i = query[0]; i <= query[2]; i++) {
                diff[i][query[1] + 1]++;
                diff[i][query[3] + 2]--;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n + 1; j++) {
                diff[i][j] = diff[i][j] + diff[i][j - 1];
            }
        }
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = diff[i][j + 1];
            }
        }
        return arr;
    }

    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] arr = new int[n][n];
        for (int[] query : queries)
            for (int i = query[0]; i <= query[2]; i++)
                for (int j = query[1]; j <= query[3]; j++)
                    arr[i][j]++;
        return arr;
    }
}
