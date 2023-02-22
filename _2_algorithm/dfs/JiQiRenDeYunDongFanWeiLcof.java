package org.example.leetcode.problems._2_algorithm.dfs;

/**
 * 面试题13.机器人的运动范围 <br>
 * 开题时间：2023-02-16 14:26:00
 */
public class JiQiRenDeYunDongFanWeiLcof {
  public static void main(String[] args) {
    Solution solution = new JiQiRenDeYunDongFanWeiLcof().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    public static final int[] DIRS_RD = {1, 0, 1};
    int ans = 0;
    private int m;
    private int n;
    private int k;
    boolean[][] vis;
  
    // dfs
    public int movingCount9(int m, int n, int k) {
      boolean[][] vis = new boolean[m][n];
      
      dfs(0, 0, k, vis);
      
      return ans;
    }
    
    private void dfs(int r, int c, int k, boolean[][] vis) {
      ans++;
      vis[r][c] = true;
      for (int i = 0; i < 4; i++) {
        int nr = r + DIRS[i];
        int nc = c + DIRS[i + 1];
        if (0 <= nr && nr < vis.length && 0 <= nc && nc < vis[0].length &&
            !vis[nr][nc] && validDigitSum(nr, nc, k)) {
          dfs(nr, nc, k, vis);
        }
      }
    }
    
    private boolean validDigitSum(int r, int c, int k) {
      return sumDigit(r) + sumDigit(c) <= k;
    }
    
    /**
     * 数位和
     */
    public static int sumDigit(long x) {
      int sum = 0;
      while (x > 0) {
        sum += x % 10;
        x /= 10;
      }
      return sum;
    }
  
    /*
     * ☆☆☆☆ dfs优化
     * 1. 起点为坐标 [0,0]，因此只需搜索 右、下 两个方向
     * 2. 不需要每次到达一个坐标就计算一次数位和，可以由上一个坐标递推出来，设：
     *     x 的数位和为 s_x
     *     x 的数位和为 s_{x+1}
     *     x 的低位中 9 的个数为 cnt（本题数据范围下，cnt 为 1）
     * 则有数位和增量公式：s_{x+1} = s_x + 1 - cnt * 9 , (x+1)%10==0
     *                          = s_x + 1 ,           (x+1)%10!=0
     */
    public int movingCount8(int m, int n, int k) {
      this.m = m;
      this.n = n;
      this.k = k;
      vis = new boolean[m][n];
    
      return dfs8(0, 0, 0, 0);
    }
  
    private int dfs8(int r, int c, int sr, int sc) {
      if (r >= m || c >= n || vis[r][c] || k < sr + sc) {
        return 0;
      }
    
      vis[r][c] = true;
    
      return 1 +
          dfs8(r + 1, c, sr + getIncr(r), sc) +
          dfs8(r, c + 1, sr, sc + getIncr(c));
    }
  
    // 数位和增量
    private int getIncr(int r) {
      return (r + 1) % 10 == 0 ? -8 : 1;
    }
  
    // ☆☆☆☆☆ dfs 函数无返回值版
    public int movingCount(int m, int n, int k) {
      this.m = m;
      this.n = n;
      this.k = k;
      vis = new boolean[m][n];
    
      dfs(0, 0, 0, 0);
    
      return ans;
    }
  
    private void dfs(int r, int c, int sr, int sc) {
      if (r >= m || c >= n || vis[r][c] || sr + sc > k) {
        return;
      }
    
      ans++;
      vis[r][c] = true;
    
      dfs(r + 1, c, getDigitSum(r, sr), sc);
      dfs(r, c + 1, sr, getDigitSum(c, sc));
    }
  
    private int getDigitSum(int i, int s) {
      return i % 10 == 9 ? s - 8 : s + 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}