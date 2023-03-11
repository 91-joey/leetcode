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
    ArrayList<Integer> list = new ArrayList<>();
    
    // 回溯
    public int numberOfPatterns(int m, int n) {
      
      this.m = m;
      this.n = n;
      vis = new boolean[10];
      
      backtrack(-1, -1, 0);
      
      return ans;
    }
    
    private void backtrack(int i, int fa, int cnt) {
      if (cnt > n) {
        return;
      }
      
      // WA点：当前序列无效，则继续dfs后的序列仍是无效的
      if (!isValid2(i, fa)) {
        return;
      }
      
      if (cnt >= m) {
        ans++;
      }
      
      for (int j = 1; j < 10; j++) {
        if (!vis[j]) {
          vis[j] = true;
          backtrack(j, i, cnt + 1);
          vis[j] = false;
        }
      }
    }
    
    private boolean isValid2(int a, int b) {
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
    
    public int numberOfPatterns9(int m, int n) {
      int res = 0;
      for (int len = m; len <= n; len++) {
        res += calcPatterns(-1, len);
        for (int i = 0; i < 9; i++) {
          used[i] = false;
        }
        list.clear();
      }
      return res;
    }
    
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
        list.forEach(v -> {
          System.out.print(v + ", ");
        });
        System.out.println();
        return 1;
      }
      int sum = 0;
      for (int i = 0; i < 9; i++) {
        if (isValid(i, last)) {
          used[i] = true;
          list.add(i + 1);
          sum += calcPatterns(i, len - 1);
          used[i] = false;
          list.remove(list.size() - 1);
        }
      }
      return sum;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}