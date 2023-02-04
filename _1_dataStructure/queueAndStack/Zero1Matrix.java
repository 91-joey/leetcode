//<p>给定一个由 <code>0</code> 和 <code>1</code> 组成的矩阵 <code>mat</code>&nbsp;，请输出一个大小相同的矩阵，其中每一个格子是 <code>mat</code> 中对应位置元素到最近的 <code>0</code> 的距离。</p>
//
//<p>两个相邻元素间的距离为 <code>1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><b>示例 1：</b></p>
//
//<p><img alt="" src="https://pic.leetcode-cn.com/1626667201-NCWmuP-mat.png" style="width: 150px; " /></p>
//
//<pre>
//<strong>输入：</strong>mat =<strong> </strong>[[0,0,0],[0,1,0],[0,0,0]]
//<strong>输出：</strong>[[0,0,0],[0,1,0],[0,0,0]]
//</pre>
//
//<p><b>示例 2：</b></p>
//
//<p><img alt="" src="https://pic.leetcode-cn.com/1626667205-xFxIeK-mat.png" style="width: 150px; " /></p>
//
//<pre>
//<b>输入：</b>mat =<b> </b>[[0,0,0],[0,1,0],[1,1,1]]
//<strong>输出：</strong>[[0,0,0],[0,1,0],[1,2,1]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == mat.length</code></li> 
// <li><code>n == mat[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li> 
// <li><code>mat[i][j] is either 0 or 1.</code></li> 
// <li><code>mat</code> 中至少有一个 <code>0&nbsp;</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍 745</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.queueAndStack;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 542.01 矩阵
// 开题时间：2022-08-24 11:59:08
public class Zero1Matrix {
  public static void main(String[] args) {
    Solution solution = new Zero1Matrix().new Solution();
    //        System.out.println(Arrays.deepToString(solution.updateMatrix(Tools.to2DIntArray("[[0,1,0],[0,1,0],[0,1,0],[0,1,0],[0,1,0]]"))));
    System.out.println(Arrays.deepToString(solution.updateMatrix(Tools.to2DIntArray("[[0,1,0,1,1],[1,1,0,0,1],[0,0,0,1,0],[1,0,1,1,1],[1,0,0,0,1]]"))));
    //        System.out.println(Arrays.deepToString(solution.updateMatrix(Tools.to2DIntArray("[[0],[0],[0],[0],[0]]"))));
    //        System.out.println(Arrays.deepToString(solution.updateMatrix(Tools.to2DIntArray("[[0,0,0],[0,1,0],[1,1,1]]"))));
    System.out.println("[[2,1,0,0,1,0,0,1,1,0],[1,0,0,1,0,1,1,2,2,1],[1,1,1,0,0,1,2,2,1,0],[0,1,2,1,0,1,2,3,2,1],[0,0,1,2,1,2,1,2,1,0],[1,1,2,3,2,1,0,1,1,1],[0,1,2,3,2,1,1,0,0,1],[1,2,1,2,1,0,0,1,1,2],[0,1,0,1,1,0,1,2,2,3],[1,2,1,0,1,0,1,2,3,4]]");
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    private final int[][] DIRECTIONS = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };
    public static final int ZERO = 0;
    
    // 1.BFS+队列
    public int[][] updateMatrix88(int[][] mat) {
      int m = mat.length;
      int n = mat[0].length;
      Queue<int[]> queue = new ArrayDeque<>();
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (mat[i][j] == ZERO) {
            queue.offer(new int[]{i, j});
          } else {
            mat[i][j] = Integer.MAX_VALUE;
          }
        }
      }
      
      int step = 1;
      while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
          int[] head = queue.poll();
          int x = head[0];
          int y = head[1];
          for (int[] direction : DIRECTIONS) {
            int xChild = x + direction[0];
            int yChild = y + direction[1];
            if (0 <= xChild && xChild < mat.length && 0 <= yChild && yChild < mat[0].length &&
                mat[xChild][yChild] == Integer.MAX_VALUE) {
              mat[xChild][yChild] = step;
              queue.offer(new int[]{xChild, yChild});
            }
          }
        }
        step++;
      }
      
      return mat;
    }
    
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    
    //☆☆☆☆ 多源BFS
    public int[][] updateMatrix9(int[][] mat) {
      int m = mat.length;
      int n = mat[0].length;
      Queue<int[]> q = new LinkedList<>();
      boolean[][] vis = new boolean[m][n];
      
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          if (mat[i][j] == 0) {
            q.offer(new int[]{i, j});
            vis[i][j] = true;
          }
      
      while (!q.isEmpty()) {
        int[] poll = q.poll();
        int r = poll[0];
        int c = poll[1];
        for (int i = 0; i < 4; i++) {
          int rNew = r + DIRS[i];
          int cNew = c + DIRS[i + 1];
          if (0 <= rNew && rNew < m && 0 <= cNew && cNew < n &&
              !vis[rNew][cNew]) {
            mat[rNew][cNew] = mat[r][c] + 1;
            q.offer(new int[]{rNew, cNew});
            vis[rNew][cNew] = true;
          }
        }
      }
      
      return mat;
    }
    
    public static final int INF = 0x3f3f3f3f;
    
    //☆☆☆☆☆ 两次DP
    public int[][] updateMatrix(int[][] mat) {
      int m = mat.length;
      int n = mat[0].length;
      int[][] f = new int[m + 2][n + 2];
      for (int i = 0; i < m + 2; i++) f[i][0] = f[i][n + 1] = INF;
      for (int j = 0; j < n + 2; j++) f[0][j] = f[m + 1][j] = INF;
      
      for (int i = 1; i < m + 1; i++)
        for (int j = 1; j < n + 1; j++)
          if (mat[i - 1][j - 1] != 0)
            f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + 1;
      
      for (int i = m; i > 0; i--)
        for (int j = n; j > 0; j--)
          if (mat[i - 1][j - 1] != 0)
            f[i][j] = Math.min(f[i][j], Math.min(f[i + 1][j], f[i][j + 1]) + 1);
      
      int[][] ans = new int[m][];
      for (int i = 0; i < m; i++)
        ans[i] = Arrays.copyOfRange(f[i + 1], 1, n + 1);
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}