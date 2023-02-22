//<p>在一个 <code>n x n</code> 的矩阵&nbsp;<code>grid</code>&nbsp;中，除了在数组&nbsp;<code>mines</code>&nbsp;中给出的元素为&nbsp;<code>0</code>，其他每个元素都为&nbsp;<code>1</code>。<code>mines[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>表示&nbsp;<code>grid[x<sub>i</sub>][y<sub>i</sub>] == 0</code></p>
//
//<p>返回 <em>&nbsp;</em><code>grid</code><em> 中包含&nbsp;<code>1</code>&nbsp;的最大的 <strong>轴对齐</strong> 加号标志的阶数</em> 。如果未找到加号标志，则返回 <code>0</code> 。</p>
//
//<p>一个&nbsp;<code>k</code>&nbsp;阶由&nbsp;<em><code>1</code></em>&nbsp;组成的 <strong>“轴对称”加号标志</strong> 具有中心网格&nbsp;<code>grid[r][c] == 1</code>&nbsp;，以及4个从中心向上、向下、向左、向右延伸，长度为&nbsp;<code>k-1</code>，由&nbsp;<code>1</code>&nbsp;组成的臂。注意，只有加号标志的所有网格要求为 <code>1</code> ，别的网格可能为 <code>0</code> 也可能为 <code>1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/06/13/plus1-grid.jpg" /></p>
//
//<pre>
//<strong>输入:</strong> n = 5, mines = [[4, 2]]
//<strong>输出:</strong> 2
//<strong>解释: </strong>在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/06/13/plus2-grid.jpg" /></p>
//
//<pre>
//<strong>输入:</strong> n = 1, mines = [[0, 0]]
//<strong>输出:</strong> 0
//<strong>解释: </strong>没有加号标志，返回 0 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 500</code></li> 
// <li><code>1 &lt;= mines.length &lt;= 5000</code></li> 
// <li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt; n</code></li> 
// <li>每一对&nbsp;<code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;都 <strong>不重复</strong>​​​​​​​</li> 
//</ul>
//
//<div><li>👍 128</li><li>👎 0</li></div>
package _2_algorithm.dp;

import java.util.Arrays;
import java.util.HashSet;

// 764.最大加号标志
// 开题时间：2022-11-09 09:19:35
public class LargestPlusSign {
  public static void main(String[] args) {
    Solution solution = new LargestPlusSign().new Solution();
    System.out.println(solution.orderOfLargestPlusSign(2, new int[][]{{0, 0}, {0, 1}, {1, 0}}));
    //        System.out.println(solution.orderOfLargestPlusSign(5, new int[][]{{4, 2}}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int orderOfLargestPlusSign9(int n, int[][] mines) {
      if (mines.length == n * n)
        return 0;
      int max = 1;
      
      HashSet<Coordinate> zeros = new HashSet<>();
      for (int[] mine : mines)
        zeros.add(new Coordinate(mine[0], mine[1]));
      
      for (int r = 1; r < n - 1; r++) {
        for (int c = 1; c < n - 1; c++) {
          if (!zeros.contains(new Coordinate(r, c))) {
            int k = 1;
            while (r - k >= 0 && r + k < n && c - k >= 0 && c + k < n) {
              if (!zeros.contains(new Coordinate(r - k, c)) &&
                  !zeros.contains(new Coordinate(r + k, c)) &&
                  !zeros.contains(new Coordinate(r, c - k)) &&
                  !zeros.contains(new Coordinate(r, c + k))
              ) {
                k++;
              } else {
                break;
              }
            }
            max = Math.max(max, k);
          }
        }
      }
      
      return max;
    }
    
    // DP    n^2 n^2
    public int orderOfLargestPlusSign8(int n, int[][] mines) {
      HashSet<Integer> zeros = new HashSet<>();
      for (int[] mine : mines)
        zeros.add(n * mine[0] + mine[1]);
      
      int[][] grid = new int[n][n];
      int maxOrder = 0;
      int cnt = 0;
      
      for (int r = 0; r < n; r++) {
        cnt = 0;
        for (int c = 0; c < n; c++) {
          if (zeros.contains(n * r + c))
            cnt = 0;
          else
            cnt++;
          grid[r][c] = cnt;
        }
        
        cnt = 0;
        for (int c = n - 1; c >= 0; c--) {
          if (zeros.contains(n * r + c))
            cnt = 0;
          else
            cnt++;
          grid[r][c] = Math.min(grid[r][c], cnt);
        }
      }
      
      for (int c = 0; c < n; c++) {
        cnt = 0;
        for (int r = 0; r < n; r++) {
          if (zeros.contains(n * r + c))
            cnt = 0;
          else
            cnt++;
          grid[r][c] = Math.min(grid[r][c], cnt);
        }
        
        cnt = 0;
        for (int r = n - 1; r >= 0; r--) {
          if (zeros.contains(n * r + c))
            cnt = 0;
          else
            cnt++;
          maxOrder = Math.max(maxOrder, Math.min(grid[r][c], cnt));
        }
      }
      
      return maxOrder;
    }
    
    // DP    n^2 n^2
    public int orderOfLargestPlusSign7(int n, int[][] mines) {
      HashSet<Integer> zeros = new HashSet<>();
      for (int[] mine : mines)
        zeros.add(n * mine[0] + mine[1]);
      
      int[][] grid = new int[n][n];
      for (int[] row : grid)
        Arrays.fill(row, n);
      
      getMinFromDirection(n, zeros, grid, true, true);
      getMinFromDirection(n, zeros, grid, true, false);
      getMinFromDirection(n, zeros, grid, false, true);
      getMinFromDirection(n, zeros, grid, false, false);
      
      return Arrays.stream(grid).flatMapToInt(Arrays::stream).max().getAsInt();
    }
    
    private void getMinFromDirection(int n, HashSet<Integer> zeros, int[][] grid, boolean horizontal, boolean natural) {
      for (int i = 0, cnt = 0; i < n; i++) {
        cnt = 0;
        for (int j = natural ? 0 : n - 1; (natural && j < n) || (!natural && j >= 0); ) {
          int site = horizontal ? n * i + j : n * j + i;
          if (zeros.contains(site))
            cnt = 0;
          else
            cnt++;
          
          if (horizontal)
            grid[i][j] = Math.min(grid[i][j], cnt);
          else
            grid[j][i] = Math.min(grid[j][i], cnt);
          
          if (natural)
            j++;
          else
            j--;
        }
      }
    }
    
    public int orderOfLargestPlusSign6(int n, int[][] mines) {
      if (mines.length == n * n)
        return 0;
      int max = 1;
      
      HashSet<Integer> zeros = new HashSet<>();
      for (int[] mine : mines)
        zeros.add(n * mine[0] + mine[1]);
      
      for (int r = 1; r < n - 1; r++) {
        for (int c = 1; c < n - 1; c++) {
          if (!zeros.contains(n * r + c)) {
            int k = 1;
            while (r - k >= 0 && r + k < n && c - k >= 0 && c + k < n) {
              if (!zeros.contains(n * (r - k) + c) &&
                  !zeros.contains(n * (r + k) + c) &&
                  !zeros.contains(n * r + c - k) &&
                  !zeros.contains(n * r + c + k)
              ) {
                k++;
              } else {
                break;
              }
            }
            max = Math.max(max, k);
          }
        }
      }
      
      return max;
    }
    
    // 暴力AC版，要点：“地雷”不能存储在哈希集合中，存储在数组中
    public int orderOfLargestPlusSign5(int n, int[][] mines) {
      if (mines.length == n * n)
        return 0;
      int max = 1;
      
      int[][] grid = new int[n][n];
      for (int[] mine : mines)
        grid[mine[0]][mine[1]] = 1;
      
      for (int r = 1; r < n - 1; r++) {
        for (int c = 1; c < n - 1; c++) {
          if (grid[r][c] != 1) {
            int k = 1;
            while (r - k >= 0 && r + k < n && c - k >= 0 && c + k < n) {
              if (grid[r - k][c] != 1 &&
                  grid[r + k][c] != 1 &&
                  grid[r][c - k] != 1 &&
                  grid[r][c + k] != 1
              ) {
                k++;
              } else {
                break;
              }
            }
            max = Math.max(max, k);
          }
        }
      }
      
      return max;
    }
    
    public static final int MINE = -1;
    
    //☆☆☆☆☆ DP（空间优化）
    public int orderOfLargestPlusSign(int n, int[][] mines) {
      
      int[][] grid = new int[n][n];
      for (int[] mine : mines)
        grid[mine[0]][mine[1]] = MINE;
      int maxOrder = 0;
      
      for (int r = 0; r < n; r++) {
        for (int c = 0, cnt = 0; c < n; c++) {
          if (grid[r][c] == MINE)
            cnt = 0;
          else {
            cnt++;
            grid[r][c] = cnt;
          }
        }
        
        for (int c = n - 1, cnt = 0; c >= 0; c--) {
          if (grid[r][c] == MINE)
            cnt = 0;
          else {
            cnt++;
            grid[r][c] = Math.min(grid[r][c], cnt);
          }
        }
      }
      
      for (int c = 0; c < n; c++) {
        for (int r = 0, cnt = 0; r < n; r++) {
          if (grid[r][c] == MINE)
            cnt = 0;
          else {
            cnt++;
            grid[r][c] = Math.min(grid[r][c], cnt);
          }
        }
        
        for (int r = n - 1, cnt = 0; r >= 0; r--) {
          if (grid[r][c] == MINE)
            cnt = 0;
          else {
            cnt++;
            maxOrder = Math.max(maxOrder, Math.min(grid[r][c], cnt));
          }
        }
      }
      
      return maxOrder;
    }
    
    class Coordinate {
      int r;
      int c;
      
      public Coordinate(int r, int c) {
        this.r = r;
        this.c = c;
      }
      
      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Coordinate that = (Coordinate) o;
        
        if (r != that.r) return false;
        return c == that.c;
      }
      
      @Override
      public int hashCode() {
        int result = r;
        result = 31 * result + c;
        return result;
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}