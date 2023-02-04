//<p>给你一个 <code>n x n</code> 的二进制矩阵 <code>grid</code> 中，返回矩阵中最短 <strong>畅通路径</strong> 的长度。如果不存在这样的路径，返回 <code>-1</code> 。</p>
//
//<p>二进制矩阵中的 畅通路径 是一条从 <strong>左上角</strong> 单元格（即，<code>(0, 0)</code>）到 右下角 单元格（即，<code>(n - 1, n - 1)</code>）的路径，该路径同时满足下述要求：</p>
//
//<ul> 
// <li>路径途经的所有单元格都的值都是 <code>0</code> 。</li> 
// <li>路径中所有相邻的单元格应当在 <strong>8 个方向之一</strong> 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。</li> 
//</ul>
//
//<p><strong>畅通路径的长度</strong> 是该路径途经的单元格总数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/example1_1.png" style="width: 500px; height: 234px;" /> 
//<pre>
//<strong>输入：</strong>grid = [[0,1],[1,0]]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/example2_1.png" style="height: 216px; width: 500px;" /> 
//<pre>
//<strong>输入：</strong>grid = [[0,0,0],[1,1,0],[1,1,0]]
//<strong>输出：</strong>4
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1,0,0],[1,1,0],[1,1,0]]
//<strong>输出：</strong>-1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == grid.length</code></li> 
// <li><code>n == grid[i].length</code></li> 
// <li><code>1 &lt;= n &lt;= 100</code></li> 
// <li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li> 
//</ul>
//
//<div><li>👍 242</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import java.util.LinkedList;
import java.util.Queue;

// 1091.二进制矩阵中的最短路径
// 开题时间：2023-01-05 14:09:49
public class ShortestPathInBinaryMatrix {
  public static void main(String[] args) {
    Solution solution = new ShortestPathInBinaryMatrix().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS8 = {1, 0, -1, 0, 1, 1, -1, -1, 1};
    
    // BFS
    public int shortestPathBinaryMatrix(int[][] grid) {
      if (grid[0][0] == 1) return -1;
      Queue<int[]> q = new LinkedList<>();
      q.offer(new int[]{0, 0});
      grid[0][0] = 1;
      
      int dist = 1;
      int n = grid.length;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          int[] poll = q.poll();
          int r = poll[0];
          int c = poll[1];
          
          if (r == n - 1 && c == n - 1)
            return dist;
          
          for (int j = 0; j < 8; j++) {
            int rNew = r + DIRS8[j];
            int cNew = c + DIRS8[j + 1];
            if (0 <= rNew && rNew < n && 0 <= cNew && cNew < n &&
                grid[rNew][cNew] == 0) {
              q.offer(new int[]{rNew, cNew});
              grid[rNew][cNew] = 1;
            }
          }
        }
        dist++;
      }
      
      return -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}