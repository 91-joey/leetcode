package org.example.leetcode.problems._9_contest.week323;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class T4 {
    public static void main(String[] args) {

    }

    public static final int[] DIRS = {-1, 0, 1, 0, -1};

    public int[] maxPointsX(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int k = queries.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int target = queries[i];
            if (grid[0][0] >= target)
                continue;
            LinkedList<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 0});
            int[][] visited = new int[m][n];
            visited[0][0] = 1;
            ans[i] = 1;

            while (!q.isEmpty()) {
                for (int j = q.size(); j > 0; j--) {
                    int[] poll = q.poll();
                    int x = poll[0];
                    int y = poll[1];
                    for (int z = 0; z < 4; z++) {
                        int r = x + DIRS[z];
                        int c = y + DIRS[z + 1];
                        if (0 <= r && r < m && 0 <= c && c < n && grid[r][c] < target) {
                            if (visited[r][c]++ == 0)
                                ans[i]++;
                            if (visited[r][c] <= 2)
                                q.offer(new int[]{r, c});
                        }
                    }
                }
            }
        }

        return ans;
    }

    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int k = queries.length;
        int[] ans = new int[k];
        int[] arr = Arrays.stream(grid).flatMapToInt(Arrays::stream).distinct().sorted().toArray();
        HashMap<Integer, Integer> map = new HashMap<>();
        LinkedList<int[]> qHard = new LinkedList<>();
        qHard.offer(new int[]{0, 0});

        for (int i = 0; i < arr.length; i++) {
            int target = queries[i];
            LinkedList<int[]> q = new LinkedList<>(qHard);
//            q.offer(new int[]{0, 0});
            int[][] visited = new int[m][n];
//            visited[0][0] = 1;
//            ans[i] = 1;
            int cnt = i == 0 ? 0 : map.get(i - 1);

            while (!q.isEmpty()) {
                for (int j = q.size(); j > 0; j--) {
                    int[] poll = q.poll();
                    int x = poll[0];
                    int y = poll[1];
                    for (int z = 0; z < 4; z++) {
                        int r = x + DIRS[z];
                        int c = y + DIRS[z + 1];
                        if (0 <= r && r < m && 0 <= c && c < n) {
                            if (grid[r][c] < target) {
                                if (visited[r][c]++ == 0)
                                    cnt++;
                                if (visited[r][c] <= i + 2)
                                    q.offer(new int[]{r, c});
                            } else {
                                qHard.offer(new int[]{r, c});
                            }
                        }
                    }
                }
            }

            map.put(arr[i], cnt);
        }

        for (int i = 0; i < k; i++) {
            ans[i] = map.getOrDefault(Arrays.binarySearch(arr, queries[i]), 0);
        }

        return ans;
    }

}
