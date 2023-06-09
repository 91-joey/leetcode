//<p>给你一个由&nbsp;<code>'1'</code>（陆地）和 <code>'0'</code>（水）组成的的二维网格，请你计算网格中岛屿的数量。</p>
//
//<p>岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。</p>
//
//<p>此外，你可以假设该网格的四条边均被水包围。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == grid.length</code></li> 
// <li><code>n == grid[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 300</code></li> 
// <li><code>grid[i][j]</code> 的值为 <code>'0'</code> 或 <code>'1'</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 1851</li><li>👎 0</li></div>
package _1_dataStructure.queueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// 200.岛屿数量
// 开题时间：2022-08-15 18:12:27
public class NumberOfIslands {
  
  public static void main(String[] args) {
    Solution solution = new NumberOfIslands().new Solution();
    System.out.println(solution.numIslands2(new char[][]{
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}
    }));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    
    public static final char LAND = '1';
    private final int[][] DIRECTIONS = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };
    
    // 1.BFS+队列
    public int numIslands(char[][] grid) {
      int cnt = 0;
      int m = grid.length;
      int n = grid[0].length;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == LAND) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{i, j});
            while (!queue.isEmpty()) {
              int[] head = queue.poll();
              int x = head[0];
              int y = head[1];
              for (int[] direction : DIRECTIONS) {
                int xChild = x + direction[0];
                int yChild = y + direction[1];
                if (0 <= xChild && xChild < m && 0 <= yChild && yChild < n &&
                    grid[xChild][yChild] == LAND) {
                  grid[xChild][yChild] = Character.MAX_VALUE;
                  queue.offer(new int[]{xChild, yChild});
                }
              }
            }
            cnt++;
          }
        }
      }
      return cnt;
    }
    
    int m;
    int n;
    
    // 2.DFS+递归（系统栈call stack）
    public int numIslands2(char[][] grid) {
      m = grid.length;
      n = grid[0].length;
      int cnt = 0;
      
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == LAND) {
            DFS(grid, i, j);
            cnt++;
          }
        }
      }
      
      return cnt;
    }
    
    private void DFS(char[][] grid, int x, int y) {
      for (int[] direction : DIRECTIONS) {
        int xChild = x + direction[0];
        int yChild = y + direction[1];
        if (0 <= xChild && xChild < m && 0 <= yChild && yChild < n &&
            grid[xChild][yChild] == LAND) {
          grid[xChild][yChild] = Character.MAX_VALUE;
          DFS(grid, xChild, yChild);
        }
      }
    }
    
    // 3.DFS+显示栈（解决递归太深导致的堆栈溢出问题）
    public int numIslands3(char[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      int cnt = 0;
      Deque<int[]> stack = new ArrayDeque<>();
      
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == LAND) {
            stack.push(new int[]{i, j});
            while (!stack.isEmpty()) {
              int[] pop = stack.pop();
              int x = pop[0];
              int y = pop[1];
              for (int[] direction : DIRECTIONS) {
                int xChild = x + direction[0];
                int yChild = y + direction[1];
                if (0 <= xChild && xChild < m && 0 <= yChild && yChild < n &&
                    grid[xChild][yChild] == LAND) {
                  grid[xChild][yChild] = Character.MAX_VALUE;
                  stack.push(new int[]{xChild, yChild});
                }
              }
            }
            cnt++;
          }
        }
      }
      
      return cnt;
    }
    
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    
    //☆☆☆☆☆ DFS
    public int numIslands9(char[][] grid) {
      int ans = 0;
      int m = grid.length;
      int n = grid[0].length;
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          if (grid[i][j] == '1') {
            ans++;
            dfs(grid, i, j);
          }
      return ans;
    }
    
    private void dfs(char[][] grid, int i, int j) {
      grid[i][j] = '0';
      for (int k = 0; k < 4; k++) {
        int newI = i + DIRS[k];
        int newJ = j + DIRS[k + 1];
        if (0 <= newI && newI < grid.length && 0 <= newJ && newJ < grid[0].length &&
            grid[newI][newJ] == '1')
          dfs(grid, newI, newJ);
      }
    }
    
    // 并查集   O(MN×α(MN))
    public int numIslands8(char[][] grid) {
      UnionFind uf = new UnionFind(grid);
      int m = grid.length;
      int n = grid[0].length;
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          if (grid[i][j] == '1') {
            grid[i][j] = '0';
            for (int k = 0; k < 4; k++) {
              int newI = i + DIRS[k];
              int newJ = j + DIRS[k + 1];
              if (0 <= newI && newI < grid.length && 0 <= newJ && newJ < grid[0].length &&
                  grid[newI][newJ] == '1')
                uf.union(i * n + j, newI * n + newJ);
            }
          }
      return uf.getCnt();
    }
  }
  
  class UnionFind {
    int[] root;
    int[] rank;
    int cnt = 0;
    
    UnionFind(char[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      root = new int[m * n];
      rank = new int[m * n];
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          if (grid[i][j] == '1') {
            cnt++;
            int idx = i * n + j;
            root[idx] = idx;
          }
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
  // leetcode submit region end(Prohibit modification and deletion)
}