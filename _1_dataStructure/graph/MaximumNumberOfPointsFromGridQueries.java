package _1_dataStructure.graph;

import _3_common.tool.Tools;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * 2503.矩阵查询可获得的最大分数 <br>
 * 开题时间：2023-03-10 11:20:14
 * _9_contest.history.week323.T4
 */
public class MaximumNumberOfPointsFromGridQueries {
  public static void main(String[] args) {
    Solution solution = new MaximumNumberOfPointsFromGridQueries().new Solution();
    System.out.println(Arrays.toString(solution.maxPoints(Tools.to2DIntArray("[[1,2,3],[2,5,7],[3,5,1]]"), new int[]{5, 6, 2})));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {-1, 0, 1, 0, -1};
    HashSet<Integer> waitList;
    int n;
    int m;
    boolean[][] vis;
    private int[][] grid;
    
    public int[] maxPointsX(int[][] grid, int[] queries) {
      this.grid = grid;
      int k = queries.length;
      int[][] query = new int[k][2];
      for (int i = 0; i < k; i++) {
        query[i][0] = queries[i];
        query[i][1] = i;
      }
      Arrays.sort(query, Comparator.comparingInt(o -> o[0]));
      
      waitList = new HashSet<>();
      waitList.add(0);
      n = grid.length;
      m = grid[0].length;
      vis = new boolean[n][m];
      
      for (int i = 0, cur = 0; i < k; i++) {
        int x = query[i][0];
        for (Integer rowCol : waitList) {
          int r = rowCol / m;
          int c = rowCol % m;
          cur += dfs(r, c, x);
        }
        query[i][0] = cur;
      }
      
      int[] ans = new int[k];
      for (int i = 0; i < k; i++) {
        ans[query[i][1]] = query[i][0];
      }
      return ans;
    }
    
    private int dfs(int r, int c, int x) {
      if (grid[r][c] >= x) {
        waitList.add(r * m + c);
        return 0;
      }
      
      int ans = 1;
      vis[r][c] = true;
      for (int i = 0; i < 4; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < grid.length && 0 <= nc && nc < grid[0].length && !vis[nr][nc]) {
          ans += dfs(nr, nc, x);
        }
      }
      return ans;
    }
    
    public static final int[] DIRS_NW = {-1, 0, -1};
  
    // 排序 + 离线询问 + 并查集
    public int[] maxPoints(int[][] grid, int[] queries) {
      int n = grid.length;
      int m = grid[0].length;
      int nm = n * m;
      // 矩阵按照元素值顺序排序（元素值捆绑坐标）
      int[][] Grid = new int[nm][3];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          Grid[i * m + j] = new int[]{grid[i][j], i, j};
        }
      }
      Arrays.sort(Grid, Comparator.comparingInt(o -> o[0]));
  
      // 查询数组按照元素值顺序排序（元素值捆绑坐标）
      int k = queries.length;
      int[][] Queries = new int[k][2];
      for (int i = 0; i < k; i++) {
        Queries[i] = new int[]{queries[i], i};
      }
      Arrays.sort(Queries, Comparator.comparingInt(o -> o[0]));
      
      int[] ans = new int[k];
      UnionFind uf = new UnionFind(nm); // 建立并查集
      // 双指针遍历 矩阵 和 查询数组
      int i = 0;
      for (int[] query : Queries) {
        int q = query[0];
        int idx = query[1];
        // 矩阵元素值小于查询值
        for (; i < nm && Grid[i][0] < q; i++) {
          int r = Grid[i][1];
          int c = Grid[i][2];
          for (int j = 0; j < DIRS.length - 1; j++) {
            int nr = r + DIRS[j];
            int nc = c + DIRS[j + 1];
            if (0 <= nr && nr < n && 0 <= nc && nc < m &&
                // 相邻矩阵元素值同样小于查询值
                grid[nr][nc] < q) {
              uf.union(r * m + c, nr * m + nc);
            }
          }
        }
  
        // 左上角小于查询值
        if (grid[0][0] < q) {
          ans[idx] = uf.size[uf.find(0)];
        }
      }
      
      return ans;
    }
  }
  
  class UnionFind {
    int[] root;
    int[] size;
    
    public UnionFind(int size) {
      root = new int[size];
      this.size = new int[size];
      for (int i = 0; i < size; i++) {
        root[i] = i;
      }
      Arrays.fill(this.size, 1);
    }
    
    public int find(int x) {
      if (x == root[x]) {
        return x;
      }
      return root[x] = find(root[x]);
    }
    
    public void union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX != rootY) {
        root[rootX] = rootY;
        size[rootY] += size[rootX];
      }
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}