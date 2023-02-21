package org.example.leetcode.problems._2_algorithm.dfs;

/**
 * 130.被围绕的区域 <br>
 * 开题时间：2023-02-15 09:25:44
 */
public class SurroundedRegions {
  public static void main(String[] args) {
    Solution solution = new SurroundedRegions().new Solution();
    // solution.solve(new char[][]{
    //     {'X', 'X', 'X', 'X'},
    //     {'X', 'O', 'O', 'X'},
    //     {'X', 'X', 'O', 'X'},
    //     {'X', 'O', 'X', 'X'}
    // });
    solution.solve(new char[][]{
        {'O', 'X', 'X', 'O', 'X'},
        {'X', 'O', 'O', 'X', 'O'},
        {'X', 'O', 'X', 'O', 'X'},
        {'O', 'X', 'O', 'O', 'O'},
        {'X', 'X', 'O', 'X', 'O'}
    });
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    
    // dfs（先dfs标记所有在边界上的 'O' ，再dfs替换所有不在边界上的 'O' 为 'X'（这里可以不用再dfs，直接双层循环遍历即可））
    public void solve9(char[][] board) {
      int n = board.length;
      int m = board[0].length;
      boolean[][] vis = new boolean[n][m];
      
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && !vis[i][j] && board[i][j] == 'O') {
            dfs(board, i, j, vis, false);
          }
        }
      }
      
      for (int i = 1; i < n - 1; i++) {
        for (int j = 1; j < m - 1; j++) {
          if (!vis[i][j] && board[i][j] == 'O') {
            dfs(board, i, j, vis, true);
          }
        }
      }
    }
    
    private void dfs(char[][] arr, int r, int c, boolean[][] vis, boolean b) {
      vis[r][c] = true;
      if (b) {
        arr[r][c] = 'X';
      }
      
      for (int i = 0; i < 4; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < arr.length && 0 <= nc && nc < arr[0].length &&
            !vis[nr][nc] && arr[nr][nc] == 'O')
          dfs(arr, nr, nc, vis, b);
      }
    }
  
    // ☆☆☆☆☆ dfs优化（先dfs标记所有在边界上的 'O' ，再双层循环遍历替换所有不在边界上的 'O' 为 'X'））
    public void solve8(char[][] board) {
      int n = board.length;
      int m = board[0].length;
      boolean[][] vis = new boolean[n][m];
      
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && !vis[i][j] && board[i][j] == 'O') {
            dfs(board, i, j, vis);
          }
        }
      }
      
      for (int i = 1; i < n - 1; i++) {
        for (int j = 1; j < m - 1; j++) {
          if (!vis[i][j] && board[i][j] == 'O') {
            board[i][j] = 'X';
          }
        }
      }
    }
    
    private void dfs(char[][] arr, int r, int c, boolean[][] vis) {
      vis[r][c] = true;
      
      for (int i = 0; i < 4; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < arr.length && 0 <= nc && nc < arr[0].length &&
            !vis[nr][nc] && arr[nr][nc] == 'O')
          dfs(arr, nr, nc, vis);
      }
    }
    
    // ☆☆☆☆ 并查集
    public void solve(char[][] board) {
      int n = board.length;
      int m = board[0].length;
      // 虚拟节点
      int dummy = n * m;
      UnionFind uf = new UnionFind(dummy + 1);
      
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
          if (board[r][c] == 'O') {
            if ((r == 0 || c == 0 || r == n - 1 || c == m - 1)) {
              // 边界上的 'O' 都与 dummy 合并成一个连通区域
              uf.union(r * m + c, dummy);
            } else {
              // 相邻的 'O' 合并
              for (int i = 0; i < 4; i++) {
                int nr = r + DIRS[i];
                int nc = c + DIRS[i + 1];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && board[nr][nc] == 'O') {
                  uf.union(nr * m + nc, r * m + c);
                }
              }
            }
          }
        }
      }
      
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
          // 和 dummy 不在一个连通区域，则替换为 'X'
          if (board[r][c] == 'O' && !uf.connected(r * m + c, dummy)) {
            board[r][c] = 'X';
          }
        }
      }
    }
  }
  
  class UnionFind {
    int[] root;
    int[] rank;
    
    public UnionFind(int size) {
      root = new int[size];
      rank = new int[size];
      for (int i = 0; i < size; i++)
        root[i] = i;
    }
    
    public int find(int x) {
      if (x == root[x])
        return x;
      return root[x] = find(root[x]);
    }
    
    public void union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX != rootY)
        if (rank[rootX] == rank[rootY]) {
          root[rootX] = rootY;
          rank[rootY]++;
        } else if (rank[rootX] < rank[rootY])
          root[rootX] = rootY;
        else
          root[rootY] = rootX;
    }
    
    public boolean connected(int x, int y) {
      return find(x) == find(y);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}