//<p>你现在手里有一份大小为
// <meta charset="UTF-8" />&nbsp;<code>n x n</code>&nbsp;的 网格 <code>grid</code>，上面的每个 单元格 都用&nbsp;<code>0</code>&nbsp;和&nbsp;<code>1</code>&nbsp;标记好了。其中&nbsp;<code>0</code>&nbsp;代表海洋，<code>1</code>&nbsp;代表陆地。</p>
//
//<p>请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回&nbsp;<code>-1</code>。</p>
//
//<p>我们这里说的距离是「曼哈顿距离」（&nbsp;Manhattan Distance）：<code>(x0, y0)</code> 和&nbsp;<code>(x1, y1)</code>&nbsp;这两个单元格之间的距离是&nbsp;<code>|x0 - x1| + |y0 - y1|</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/08/17/1336_ex1.jpeg" /></strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1,0,1],[0,0,0],[1,0,1]]
//<strong>输出：</strong>2
//<strong>解释： </strong>
// 海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/08/17/1336_ex2.jpeg" /></strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1,0,0],[0,0,0],[0,0,0]]
//<strong>输出：</strong>4
//<strong>解释： </strong>
// 海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<p>
// <meta charset="UTF-8" /></p>
//
//<ul> 
// <li><code>n == grid.length</code></li> 
// <li><code>n == grid[i].length</code></li> 
// <li><code>1 &lt;= n&nbsp;&lt;= 100</code></li> 
// <li><code>grid[i][j]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code></li> 
//</ul>
//
//<div><li>👍 311</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 1162.地图分析
// 开题时间：2023-01-04 12:21:57
public class AsFarFromLandAsPossible {
  public static void main(String[] args) {
    Solution solution = new AsFarFromLandAsPossible().new Solution();
    System.out.println(solution.maxDistance(Tools.to2DIntArray("[[1,0,0,0,0,1,0,0,0,1],[1,1,0,1,1,1,0,1,1,0],[0,1,1,0,1,0,0,1,0,0],[1,0,1,0,1,0,0,0,0,0],[0,1,0,0,0,1,1,0,1,1],[0,0,1,0,0,1,0,1,0,1],[0,0,0,1,1,1,1,0,0,1],[0,1,0,0,1,0,0,1,0,0],[0,0,0,0,0,1,1,1,0,0],[1,1,0,1,1,1,1,1,0,0]]")));
    //        System.out.println(solution.maxDistance(Tools.to2DIntArray("[[1,0,1],[0,0,0],[1,0,1]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    
    // 单源BFS
    public int maxDistanceTLE(int[][] grid) {
      int max = -1;
      int sum = Arrays.stream(grid).flatMapToInt(Arrays::stream).sum();
      int n = grid.length;
      if (sum == 0 || sum == n * n)
        return max;
      
      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
          if (grid[i][j] == 0)
            max = Math.max(max, bfs(grid, i, j));
      
      return max;
    }
    
    //☆☆☆☆☆ 多源BFS（也可以看成是单源，源为虚拟的超级源点）
    public int maxDistance(int[][] grid) {
      int n = grid.length;
      
      Queue<int[]> q = new LinkedList<>();
      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
          if (grid[i][j] == 1)
            q.offer(new int[]{i, j});
      
      if (q.isEmpty() || q.size() == n * n)
        return -1;
      // 记录最后一个被访问的海洋单元格
      int[] furthestOcean = new int[0];
      while (!q.isEmpty()) {
        furthestOcean = q.poll();
        int r = furthestOcean[0];
        int c = furthestOcean[1];
        
        for (int k = 0; k < 4; k++) {
          int newI = r + DIRS[k];
          int newJ = c + DIRS[k + 1];
          if (0 <= newI && newI < n && 0 <= newJ && newJ < n &&
              grid[newI][newJ] == 0) {
            grid[newI][newJ] = grid[r][c] + 1;
            q.offer(new int[]{newI, newJ});
          }
        }
      }
      
      return grid[furthestOcean[0]][furthestOcean[1]] - 1;
    }
    
    private int bfs(int[][] grid, int i, int j) {
      int m = grid.length;
      int n = grid[0].length;
      
      Queue<int[]> q = new LinkedList<>();
      boolean[][] vis = new boolean[m][n];
      q.offer(new int[]{i, j});
      vis[i][j] = true;
      
      int distance = 0;
      while (!q.isEmpty()) {
        for (int size = q.size(); size > 0; size--) {
          int[] poll = q.poll();
          int r = poll[0];
          int c = poll[1];
          
          if (grid[r][c] == 1)
            return distance;
          
          vis[r][c] = true;
          
          for (int k = 0; k < 4; k++) {
            int newI = r + DIRS[k];
            int newJ = c + DIRS[k + 1];
            if (0 <= newI && newI < m && 0 <= newJ && newJ < n &&
                !vis[newI][newJ])
              q.offer(new int[]{newI, newJ});
          }
        }
        distance++;
      }
      
      return distance;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}