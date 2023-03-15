import _3_common.tool.Tools;

import java.util.Arrays;

/**
 * 847.访问所有节点的最短路径 <br>
 * 开题时间：2023-03-15 17:48:51
 */
public class ShortestPathVisitingAllNodes {
  public static void main(String[] args) {
    Solution solution = new ShortestPathVisitingAllNodes().new Solution();
    System.out.println(solution.shortestPathLength(Tools.to2DIntArray("[[1,2,3],[0],[0],[0]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int shortestPathLength(int[][] graph) {
      int n = graph.length;
      boolean[][] g = new boolean[n][n];
      for (int u = 0; u < n; u++) {
        for (int v : graph[u]) {
          g[u][v] = g[v][u] = true;
        }
      }
      
      int[][] f = new int[1 << n][n];
      for (int s = 0; s < 1 << n; s++) {
        Arrays.fill(f[s], 0x3f3f3f3f);
      }
      for (int i = 0; i < n; i++) {
        f[1 << i][i] = 1;
      }
      
      for (int s = 1; s < 1 << n; s++) {
        for (int i = 0; i < n; i++) {
          if ((s & (1 << i)) == 0) {
            continue;
          }
          for (int j = 0; j < n; j++) {
            if ((s & (1 << j)) == 0 || !g[i][j]) {
              continue;
            }
            f[s][i] = Math.min(f[s][i], f[s][j] + 1);
          }
        }
      }
      
      return Arrays.stream(f[(1 << n) - 1]).min().getAsInt();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}