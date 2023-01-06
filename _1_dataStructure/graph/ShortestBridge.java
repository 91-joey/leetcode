//<p>给你一个大小为 <code>n x n</code> 的二元矩阵 <code>grid</code> ，其中 <code>1</code> 表示陆地，<code>0</code> 表示水域。</p>
//
//<p><strong>岛</strong> 是由四面相连的 <code>1</code> 形成的一个最大组，即不会与非组内的任何其他 <code>1</code> 相连。<code>grid</code> 中 <strong>恰好存在两座岛</strong> 。</p>
//
//<div class="original__bRMd"> 
// <div> 
//  <p>你可以将任意数量的 <code>0</code> 变为 <code>1</code> ，以使两座岛连接起来，变成 <strong>一座岛</strong> 。</p> 
// </div>
//</div>
//
//<p>返回必须翻转的 <code>0</code> 的最小数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[0,1],[1,0]]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[0,1,0],[0,0,0],[0,0,1]]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == grid.length == grid[i].length</code></li> 
// <li><code>2 &lt;= n &lt;= 100</code></li> 
// <li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li> 
// <li><code>grid</code> 中恰有两个岛</li> 
//</ul>
//
//<div><li>👍 420</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.LinkedList;
import java.util.Queue;

//934.最短的桥
//开题时间：2023-01-06 11:39:59
public class ShortestBridge {
    public static void main(String[] args) {
        Solution solution = new ShortestBridge().new Solution();
        System.out.println(solution.shortestBridge(Tools.to2DIntArray("[[0,1],[1,0]]")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int[] DIRS = {1, 0, -1, 0, 1};

        //DFS+多源BFS
        public int shortestBridge(int[][] grid) {
            int n = grid.length;
            Queue<int[]> q = new LinkedList<>();
            boolean[][] vis = new boolean[n][n];
            out:
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (grid[i][j] == 1) {
                        dfs(grid, i, j, q, vis);
                        break out;
                    }

            int ans = 0;
            while (!q.isEmpty()) {
                for (int i = q.size(); i > 0; i--) {
                    int[] poll = q.poll();
                    int r = poll[0];
                    int c = poll[1];
                    for (int j = 0; j < 4; j++) {
                        int rNew = r + DIRS[j];
                        int cNew = c + DIRS[j + 1];
                        if (0 <= rNew && rNew < n && 0 <= cNew && cNew < n &&
                                !vis[rNew][cNew]) {
                            if (grid[rNew][cNew] == 1)
                                return ans;
                            q.offer(new int[]{rNew, cNew});
                            vis[rNew][cNew] = true;
                        }
                    }
                }
                ans++;
            }
            return ans;
        }

        public void dfs(int[][] arr, int r, int c, Queue<int[]> q, boolean[][] vis) {
            q.offer(new int[]{r, c});
            vis[r][c] = true;
            for (int i = 0; i < 4; i++) {
                int rNew = r + DIRS[i];
                int cNew = c + DIRS[i + 1];
                if (0 <= rNew && rNew < arr.length && 0 <= cNew && cNew < arr[0].length &&
                        !vis[rNew][cNew] && arr[rNew][cNew] == 1)
                    dfs(arr, rNew, cNew, q, vis);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}