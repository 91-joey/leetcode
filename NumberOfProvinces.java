//<div class="original__bRMd"> 
// <div> 
//  <p>有 <code>n</code> 个城市，其中一些彼此相连，另一些没有相连。如果城市 <code>a</code> 与城市 <code>b</code> 直接相连，且城市 <code>b</code> 与城市 <code>c</code> 直接相连，那么城市 <code>a</code> 与城市 <code>c</code> 间接相连。</p> 
// </div>
//</div>
//
//<p><strong>省份</strong> 是一组直接或间接相连的城市，组内不含其他没有相连的城市。</p>
//
//<p>给你一个 <code>n x n</code> 的矩阵 <code>isConnected</code> ，其中 <code>isConnected[i][j] = 1</code> 表示第 <code>i</code> 个城市和第 <code>j</code> 个城市直接相连，而 <code>isConnected[i][j] = 0</code> 表示二者不直接相连。</p>
//
//<p>返回矩阵中 <strong>省份</strong> 的数量。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/12/24/graph1.jpg" style="width: 222px; height: 142px;" /> 
//<pre>
//<strong>输入：</strong>isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/12/24/graph2.jpg" style="width: 222px; height: 142px;" /> 
//<pre>
//<strong>输入：</strong>isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 200</code></li> 
// <li><code>n == isConnected.length</code></li> 
// <li><code>n == isConnected[i].length</code></li> 
// <li><code>isConnected[i][j]</code> 为 <code>1</code> 或 <code>0</code></li> 
// <li><code>isConnected[i][i] == 1</code></li> 
// <li><code>isConnected[i][j] == isConnected[j][i]</code></li> 
//</ul>
//
//<div><li>👍 910</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//547.省份数量
//开题时间：2022-12-23 17:29:03
public class NumberOfProvinces {
    public static void main(String[] args) {
        Solution solution = new NumberOfProvinces().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] root;

        //n^3 并查集（quick find实现 + 去重计数）
        public int findCircleNum9(int[][] isConnected) {
            int n = isConnected.length;
            root = new int[n];
            for (int i = 0; i < n; i++)
                root[i] = i;

            for (int i = 0; i < n; i++)
                for (int j = i; j < n; j++)
                    if (isConnected[i][j] == 1)
                        union(i, j);

            return (int) Arrays.stream(root).distinct().count();
        }

        //☆☆☆ n^2 * log n 并查集（quick union实现 + 计数）
        public int findCircleNum8(int[][] isConnected) {
            int n = isConnected.length;
            UnionFind uf = new UnionFind(n);

            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    if (isConnected[i][j] == 1)
                        uf.union(i, j);

            return uf.getCnt();
        }

        //☆☆☆☆☆ n^2 DFS
        public int findCircleNum7(int[][] isConnected) {
            int ans = 0;
            int n = isConnected.length;
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(isConnected, visited, i);
                    ans++;
                }
            }
            return ans;
        }

        private void dfs(int[][] isConnected, boolean[] visited, int i) {
            for (int j = 0; j < isConnected.length; j++) {
                if (!visited[j] && isConnected[i][j] == 1) {
                    visited[j] = true;
                    dfs(isConnected, visited, j);
                }
            }
        }

        //☆☆☆☆☆ n^2 BFS
        public int findCircleNum(int[][] isConnected) {
            int ans = 0;
            int n = isConnected.length;
            boolean[] visited = new boolean[n];
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < n; i++)
                if (!visited[i]) {
                    q.offer(i);
                    while (!q.isEmpty()) {
                        int poll = q.poll();
                        visited[poll] = true;
                        for (int j = 0; j < n; j++)
                            if (!visited[j] && isConnected[poll][j] == 1)
                                q.offer(j);
                    }
                    ans++;
                }
            return ans;
        }

        class UnionFind {
            int[] root;
            int[] rank;
            int cnt;

            UnionFind(int size) {
                root = new int[size];
                rank = new int[size];
                cnt = size;
                for (int i = 0; i < size; i++)
                    root[i] = i;
            }

            int find(int x) {
                if (x == root[x])
                    return x;
                return root[x] = find(root[x]);
            }

            void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX != rootY) {
                    if (rank[rootX] > rank[rootY]) {
                        root[rootY] = rootX;
                    } else if (rank[rootX] < rank[rootY]) {
                        root[rootX] = rootY;
                    } else {
                        root[rootY] = rootX;
                        rank[rootX]++;
                    }
                    cnt--;
                }
            }

            int getCnt() {
                return cnt;
            }
        }

        //O(1)
        public int find(int x) {
            return root[x];
        }

        //O(n)  元素值始终为根节点
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY)
                for (int i = 0; i < root.length; i++)
                    if (root[i] == rootY)
                        root[i] = rootX;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}