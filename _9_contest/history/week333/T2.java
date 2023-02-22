package _9_contest.history.week333;

// 2571. Minimum Operations to Reduce an Integer to 0
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution.minOperations(27));
    System.out.println(Integer.toBinaryString(27));
    System.out.println(solution.minOperations(39));
    System.out.println(Integer.toBinaryString(39));
    System.out.println(solution.minOperations(54));
    System.out.println(Integer.toBinaryString(54));
    
    System.out.println(8 & -8);
  }
  
  class Solution {
    public int minOperationsX(int n) {
      int cnt = Integer.bitCount(n);
      int leadingZeros = Integer.numberOfLeadingZeros(n);
      return Math.min(cnt, 32 - leadingZeros - cnt + 2);
    }
  
    int ans = 32;
  
    int bound;
    
    // dfs  3^1ogn
    public int minOperations9(int n) {
      bound = 32 - Integer.numberOfLeadingZeros(n);
      dfs(n, 0, 0);
      
      return ans;
    }
    
    /**
     * 每一位都有 3 种选择：
     * - 加法
     * - 减法
     * - 什么都不做
     *
     * @param n    当前数值
     * @param tier 二进制从低位开始的序号（从 0 开始）
     * @param cnt  操作数
     */
    private void dfs(int n, int tier, int cnt) {
      // 剪枝
      if (ans < cnt) {
        return;
      }
      if (tier == bound + 1) {
        if (n == 0) {
          ans = Math.min(ans, cnt);
        }
        return;
      }
  
      // 什么都不做、加法 优先进行dfs
      dfs(n, tier + 1, cnt);
      dfs(n + (1 << tier), tier + 1, cnt + 1);
      dfs(n - (1 << tier), tier + 1, cnt + 1);
    }
    
    /*
     * ☆☆☆☆☆ 位运算 + 贪心
     * 在低位上做加减运算，可能会对高位产生影响，因此优先考虑消除低位的 1
     * 贪心策略：若有多个连续 1 （>=2)，则加法更优；否则减法更优
     */
    public int minOperations8(int n) {
      int ans = 0;
      while (n > 0) {
        int lb = n & -n;
        if ((n & (lb << 1)) > 0) {
          n += lb;
        } else {
          n -= lb;
        }
        ans++;
      }
      return ans;
    }
    
    // 位运算优化
    public int minOperations(int n) {
      return Integer.bitCount(n ^ 3 * n);
    }
  }
}
