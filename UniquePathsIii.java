
/**
 * 980.不同路径 III <br>
 * 开题时间：2023-03-14 17:17:54
 */
public class UniquePathsIii {
  public static void main(String[] args) {
    Solution solution = new UniquePathsIii().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    int n;
    int m;
    private int[][] grid;
    boolean[][] vis;
    int ans = 0;
    Integer[][][] memo;
    int t;
    
    // 标记数组 + 回溯
    public int uniquePathsIII9(int[][] grid) {
      this.grid = grid;
      n = grid.length;
      m = grid[0].length;
      vis = new boolean[n][m];
      
      int r = 0;
      int c = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (grid[i][j] == 1) {
            r = i;
            c = j;
            vis[i][j] = true;
          } else if (grid[i][j] == -1) {
            vis[i][j] = true;
          }
        }
      }
      
      backtrack(r, c);
      
      return ans;
    }
    
    private void backtrack(int r, int c) {
      if (grid[r][c] == 2) {
        boolean allPassed = true;
        out:
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (!vis[i][j]) {
              allPassed = false;
              break out;
            }
          }
        }
        if (allPassed) {
          ans++;
        }
      }
      
      for (int i = 0; i < DIRS.length - 1; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < n && 0 <= nc && nc < m &&
            !vis[nr][nc]) {
          vis[nr][nc] = true;
          backtrack(nr, nc);
          vis[nr][nc] = false;
        }
      }
    }
  
    // 标记数组 + 回溯（优化：引入 togo 变量，记录待行走方格数）
    public int uniquePathsIII8(int[][] grid) {
      this.grid = grid;
      n = grid.length;
      m = grid[0].length;
      vis = new boolean[n][m];
      
      int r = 0;
      int c = 0;
      int togo = 0; // 需要通过的方格数（包括空方格和结束方格）
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (grid[i][j] == 1) {
            r = i;
            c = j;
            vis[i][j] = true;
          } else if (grid[i][j] == -1) {
            vis[i][j] = true;
          } else {
            togo++;
          }
        }
      }
      
      backtrack(r, c, togo);
      
      return ans;
    }
    
    private void backtrack(int r, int c, int togo) {
      if (grid[r][c] == 2) {
        if (togo == 0) {
          ans++;
        }
        return;
      }
      
      for (int i = 0; i < DIRS.length - 1; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < n && 0 <= nc && nc < m &&
            !vis[nr][nc]) {
          vis[nr][nc] = true;
          backtrack(nr, nc, togo - 1);
          vis[nr][nc] = false;
        }
      }
    }
    
    // 状压 + 记搜
    public int uniquePathsIII(int[][] grid) {
      this.grid = grid;
      n = grid.length;
      m = grid[0].length;
      memo = new Integer[n][m][1 << n * m];
      t = (1 << n * m) - 1;
      
      int r = 0;
      int c = 0;
      int s = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (grid[i][j] == 1) {
            r = i;
            c = j;
            s |= 1 << i * m + j;
          } else if (grid[i][j] == -1) {
            s |= 1 << i * m + j;
          }
        }
      }
      
      return dfs(r, c, s);
    }
    
    private int dfs(int r, int c, int s) {
      if (memo[r][c][s] != null) {
        return memo[r][c][s];
      }
      
      if (grid[r][c] == 2) {
        if (s == t) {
          return memo[r][c][s] = 1;
        }
        return memo[r][c][s] = 0;
      }
      
      memo[r][c][s] = 0;
      for (int i = 0; i < DIRS.length - 1; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < n && 0 <= nc && nc < m &&
            (s & (1 << nr * m + nc)) == 0) {
          memo[r][c][s] += dfs(nr, nc, s | (1 << nr * m + nc));
        }
      }
      
      return memo[r][c][s];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}