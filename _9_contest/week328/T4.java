package org.example.leetcode.problems._9_contest.week328;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
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
            dfsX(graph, vis, idx, price, 0);
            return max;
        }

        private void dfsX(List<Integer>[] graph, boolean[] vis, int i, int[] price, long sum) {
            vis[i] = true;
            if (graph[i].size() == 1) {
                max = Math.max(max, sum);
            }
            for (int j : graph[i]) {
                if (!vis[j]) {
                    dfsXX(graph, vis, j, price, sum + price[i]);
                }
            }
        }

        public long maxOutputXX(int n, int[][] edges, int[] price) {
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
            dfsXX(graph, vis, idx, price, 0);
            return max;
        }

        private void dfsXX(List<Integer>[] graph, boolean[] vis, int i, int[] price, long sum) {
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
                    dfsXX(graph, vis, j, price, sum);
                }
            }
        }

        List<Integer>[] g;
        int[] price;
        long ans;

        /*
         * 树形DP
         * 某节点为根节点：
         *      价值和最大的路径必为此节点到叶节点的价值和
         *      价值和最小的路径必为此节点的价值
         * 因此，某节点为根节点的开销 = 此节点到叶节点的价值和 - 某一端点的价值
         */
        public long maxOutput(int n, int[][] edges, int[] price) {
            g = new ArrayList[n];
            this.price = price;
            Arrays.setAll(g, idx -> new ArrayList<>());
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
                g[edge[1]].add(edge[0]);
            }
            dfs(0, -1);
            return ans;
        }

        /**
         *
         * @param x 当前遍历的节点
         * @param fa 父节点
         * @return 数组{最大路径和（带叶节点），最大路径和（不带叶节点）}
         */
        private long[] dfs(int x, int fa) {
            long p = price[x], max_s1 = p, max_s2 = 0;
            for (int y : g[x]) {
                if (y != fa) {
                    long[] maxes = dfs(y, x);
                    long s1 = maxes[0], s2 = maxes[1];
                    //最大开销即最大路径和（去除某一端点），其值为以下两种情况中的较大值：
                    //1.当前子树的最大路径和（带叶节点）    + 另一子树的最大路径和（不带叶节点）
                    //2.当前子树的最大路径和（不带叶节点）  + 另一子树的最大路径和（带叶节点）
                    ans = Math.max(ans, Math.max(max_s1 + s2, max_s2 + s1));
                    max_s1 = Math.max(max_s1, s1 + p);
                    max_s2 = Math.max(max_s2, s2 + p);
                }
            }
            return new long[]{max_s1, max_s2};
        }
    }
}
