import java.util.ArrayList;

/**
 * 351.安卓系统手势解锁 <br>
 * 开题时间：2023-03-11 15:56:49
 */
public class AndroidUnlockPatterns {
  public static void main(String[] args) {
    Solution solution = new AndroidUnlockPatterns().new Solution();
    System.out.println(solution.numberOfPatterns(3, 3));
    // System.out.println(solution.numberOfPatterns(3, 3));
    // System.out.println(solution.numberOfPatterns(2, 2));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    private int m;
    private int n;
    int ans = 0;
    boolean[] vis;
  
    // ☆☆☆☆ 回溯
    public int numberOfPatterns(int m, int n) {
      
      this.m = m;
      this.n = n;
      vis = new boolean[10];
      
      backtrack(-1, -1, 0);
      
      return ans;
    }
    
    /**
     * @param i   当前点（初始为 -1，表示没有点）
     * @param fa  上一个点（初始为 -1，表示没有上一个点）
     * @param cnt 点的数量（初始为 0，表示没有点）
     */
    private void backtrack(int i, int fa, int cnt) {
      if (cnt > n) {
        return;
      }
      
      // WA点：当前序列无效，则继续dfs后的序列仍是无效的
      if (!isValid9(i, fa)) {
        return;
      }
      
      if (cnt >= m) {
        ans++;
      }
      
      for (int j = 1; j <= 9; j++) {
        if (!vis[j]) {
          vis[j] = true;
          backtrack(j, i, cnt + 1);
          vis[j] = false;
        }
      }
    }
    
    
    public static final int[][] CENTER = new int[10][10];
    
    // 两点经过的其他点的中心（不经过则为 0）
    static {
      CENTER[1][3] = 2;
      CENTER[1][7] = 4;
      CENTER[3][9] = 6;
      CENTER[7][9] = 8;
      CENTER[1][9] = CENTER[2][8] = CENTER[3][7] = CENTER[4][6] = 5;
    }
    
    private boolean isValid9(int a, int b) {
      if (a > b) {
        int tmp = a;
        a = b;
        b = tmp;
      }
      
      return a == -1 || // <= 1 个点
          CENTER[a][b] == 0 || // 不经过其他点的中心
          vis[CENTER[a][b]]; // 经过其他点的中心，且这个点已经提前经过
    }
    
    private boolean isValid99(int a, int b) {
      if (a > b) {
        int tmp = a;
        a = b;
        b = tmp;
      }
      
      if (a == 1 && b == 3) {
        if (!vis[2]) {
          return false;
        }
      } else if (a == 1 && b == 7) {
        if (!vis[4]) {
          return false;
        }
      } else if (a == 3 && b == 9) {
        if (!vis[6]) {
          return false;
        }
      } else if (a == 7 && b == 9) {
        if (!vis[8]) {
          return false;
        }
      } else if (a + b == 10) {
        if (!vis[5]) {
          return false;
        }
      }
      
      return true;
    }
    
    private boolean used[] = new boolean[9];
    
    // 回溯（官解）
    public int numberOfPatterns8(int m, int n) {
      int res = 0;
      for (int len = m; len <= n; len++) {
        res += calcPatterns(-1, len);
      }
      return res;
    }
    
    // 仅供参考，效率不高
    private boolean isValid(int index, int last) {
      if (used[index])
        return false;
      // first digit of the pattern
      if (last == -1)
        return true;
      // knight moves or adjacent cells (in a row or in a column)
      if ((index + last) % 2 == 1)
        return true;
      // indexes are at both end of the diagonals for example 0,0, and 8,8
      int mid = (index + last) / 2;
      if (mid == 4)
        return used[mid];
      // adjacent cells on diagonal  - for example 0,0 and 1,0 or 2,0 and //1,1
      if ((index % 3 != last % 3) && (index / 3 != last / 3)) {
        return true;
      }
      // all other cells which are not adjacent
      return used[mid];
    }
    
    private int calcPatterns(int last, int len) {
      if (len == 0) {
        return 1;
      }
      int sum = 0;
      for (int i = 0; i < 9; i++) {
        if (isValid(i, last)) {
          used[i] = true;
          sum += calcPatterns(i, len - 1);
          used[i] = false;
        }
      }
      return sum;
    }
    
    public static final int[][] DICT = new int[9][9];
    
    static {
      // 横
      DICT[0][2] = DICT[2][0] = (1 << 1);
      DICT[3][5] = DICT[5][3] = (1 << 4);
      DICT[6][8] = DICT[8][6] = (1 << 7);
      // 竖
      DICT[0][6] = DICT[6][0] = (1 << 3);
      DICT[1][7] = DICT[7][1] = (1 << 4);
      DICT[2][8] = DICT[8][2] = (1 << 5);
      // 对角
      DICT[0][8] = DICT[8][0] = (1 << 4);
      DICT[2][6] = DICT[6][2] = (1 << 4);
    }
    
    /*
     * ☆☆☆☆☆ 状压dp
     * 状态定义：f[len][i][s] := len+1 个点、最后一个点位 i+1、数字的选中状态为 s 的解锁模式的个数
     * 状态转移：f[len][i][s | (1 << i)] += f[len - 1][j][s] if 状态 s 选中 j 、未选中 i
     * 初始化　：f[0][i][1 << i] for 0 <= i < 9
     * 答案　　：∑ f[len][i][s] for m - 1 <= len <= n - 1
     */
    public int numberOfPatterns7(int m, int n) {
      int[][][] f = new int[n][9][1 << 9];
      for (int i = 0; i < 9; i++) {
        f[0][i][1 << i] = 1;
      }
      
      for (int len = 1; len < n; len++) {
        for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
            for (int s = 0; s < 1 << 9; s++) {
              if (f[len - 1][j][s] == 0 || (s & (1 << i)) != 0) {
                continue;
              }
              if (isValid(i, j, s)) {
                f[len][i][s | (1 << i)] += f[len - 1][j][s];
              }
            }
          }
        }
      }
      
      int ans = 0;
      for (int len = m - 1; len <= n - 1; len++) {
        for (int i = 0; i < 9; i++) {
          for (int s = 0; s < 1 << 9; s++) {
            ans += f[len][i][s];
          }
        }
      }
      return ans;
    }
    
    // 状压dp2（效率较低）
    public int numberOfPatterns6(int m, int n) {
      int[][][] f = new int[n][9][1 << 9];
      for (int i = 0; i < 9; i++) {
        f[0][i][1 << i] = 1;
      }
      
      int ans = 0;
      for (int len = 1; len < n; len++) {
        for (int i = 0; i < 9; i++) {
          for (int s = 0; s < 1 << 9; s++) {
            if ((s & (1 << i)) == 0) {
              continue;
            }
            for (int j = 0; j < 9; j++) {
              if (i == j || (s & (1 << j)) == 0) {
                continue;
              }
              int preS = s ^ (1 << i);
              if (isValid(i, j, preS)) {
                f[len][i][s] += f[len - 1][j][preS];
              }
            }
            
            if (len >= m - 1) {
              ans += f[len][i][s];
            }
          }
        }
      }
      
      return m == 1 ? ans + 9 : ans;
    }
    
    private boolean isValid(int i, int j, int s) {
      return DICT[i][j] == 0 || // 未经过其他点的中心
          (DICT[i][j] & s) != 0; // 经过其他点的中心、且状态 s 已经选中该中心点
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}