package org.example.leetcode.problems._9_contest.week328;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;

//6292. Increment Submatrices by One
public class T2 {
    public static void main(String[] args) {
        Solution solution = new T2().new Solution();
        System.out.println(Arrays.deepToString(solution.rangeAddQueries(3, Tools.to2DIntArray("[[1,1,2,2],[0,0,1,1]]"))));
    }

    class Solution {
        public int[][] rangeAddQueriesX(int n, int[][] queries) {
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

        //一维差分  len(queries)*n + n^2
        public int[][] rangeAddQueries9(int n, int[][] queries) {
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

        //暴力    len(queries)*n^2
        public int[][] rangeAddQueries8(int n, int[][] queries) {
            int[][] arr = new int[n][n];
            for (int[] query : queries)
                for (int i = query[0]; i <= query[2]; i++)
                    for (int j = query[1]; j <= query[3]; j++)
                        arr[i][j]++;
            return arr;
        }

        //一维差分（优化）
        public int[][] rangeAddQueries7(int n, int[][] queries) {
            int[][] diff = new int[n][n];
            for (int[] query : queries) {
                for (int i = query[0]; i <= query[2]; i++) {
                    diff[i][query[1]]++;
                    if (query[3] + 1 < n)
                        diff[i][query[3] + 1]--;
                }
            }
            for (int i = 0; i < n; i++)
                for (int j = 1; j < n; j++)
                    diff[i][j] = diff[i][j] + diff[i][j - 1];
            return diff;
        }

        //☆☆☆☆☆ 二维差分
        public int[][] rangeAddQueries(int n, int[][] queries) {
            int[][] diff = new int[n + 2][n + 2];
            for (int[] query : queries) {
                int r1 = query[0] + 1, c1 = query[1] + 1;
                int r2 = query[2] + 2, c2 = query[3] + 2;
                diff[r1][c1]++;
                diff[r1][c2]--;
                diff[r2][c1]--;
                diff[r2][c2]++;
            }
            for (int i = 1; i < n + 1; i++)
                for (int j = 1; j < n + 1; j++)
                    diff[i][j] = diff[i][j] + diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];

            int[][] ans = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    ans[i][j] = diff[i + 1][j + 1];
            return ans;
        }
    }
}
