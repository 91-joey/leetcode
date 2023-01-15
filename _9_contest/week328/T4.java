package org.example.leetcode.problems._9_contest.week328;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.List;

//6294. Difference Between Maximum and Minimum Price Sum
public class T4 {
    public static void main(String[] args) {
        Solution solution = new T4().new Solution();
//        System.out.println(s.maxOutput(9, Tools.to2DIntArray("[[1,7],[5,2],[2,3],[6,0],[0,4],[4,7],[7,3],[3,8]]"), new int[]{6, 13, 8, 10, 4, 5, 8, 3, 12}));
        System.out.println(solution.maxOutput(6, Tools.to2DIntArray("[[0,1],[1,2],[1,3],[3,4],[3,5]]"), new int[]{9, 8, 7, 6, 10, 5}));
    }

    class Solution {
        long max = 0;

        public long maxOutputX(int n, int[][] edges, int[] price) {
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            int[] deg = new int[n];
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
                deg[edge[0]]++;
                deg[edge[1]]++;
            }
            int idx = 0, maxVal = 0;
            for (int i = 0; i < n; i++) {
                if (deg[i] == 1 && price[i] > maxVal) {
                    idx = i;
                    maxVal = price[i];
                }
            }

            boolean[] vis = new boolean[n];
            dfs(graph, vis, idx, price, 0);
            return max;
        }

        private void dfsX(List<Integer>[] graph, boolean[] vis, int i, int[] price, long sum) {
            vis[i] = true;
            if (graph[i].size() == 1) {
                max = Math.max(max, sum);
            }
            for (int j : graph[i]) {
                if (!vis[j]) {
                    dfs(graph, vis, j, price, sum + price[i]);
                }
            }
        }

        public long maxOutput(int n, int[][] edges, int[] price) {
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            int[] deg = new int[n];
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
                deg[edge[0]]++;
                deg[edge[1]]++;
            }
            int idx = 0, maxVal = 0;
            for (int i = 0; i < n; i++) {
                if (deg[i] == 1 && price[i] > maxVal) {
                    idx = i;
                    maxVal = price[i];
                }
            }

            boolean[] vis = new boolean[n];
            dfs(graph, vis, idx, price, 0);
            return max;
        }

        private void dfs(List<Integer>[] graph, boolean[] vis, int i, int[] price, long sum) {
//            if (graph[i].size() == 1) {
            if (!vis[i]) {
                sum += price[i];
            }
            vis[i] = true;
            max = Math.max(max, sum);
//            }

//            if (graph[i].size() == 1)
//                return;
            for (int j : graph[i]) {
                if (!vis[j]) {
                    dfs(graph, vis, j, price, sum);
                }
            }
        }

        //todo 树形DP
    }
}
